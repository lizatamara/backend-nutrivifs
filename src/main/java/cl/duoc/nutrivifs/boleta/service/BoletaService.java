package cl.duoc.nutrivifs.boleta.service;

import cl.duoc.nutrivifs.boleta.dto.BoletaDTO;
import cl.duoc.nutrivifs.boleta.dto.BoletaRequest;
import cl.duoc.nutrivifs.boleta.dto.ClienteDTO;
import cl.duoc.nutrivifs.boleta.dto.ItemBoletaDTO;
import cl.duoc.nutrivifs.boleta.dto.ItemRequest;
import cl.duoc.nutrivifs.boleta.model.Boleta;
import cl.duoc.nutrivifs.boleta.model.DetalleBoleta;
import cl.duoc.nutrivifs.boleta.repository.BoletaRepository;
import cl.duoc.nutrivifs.exception.NoEncontradoException;
import cl.duoc.nutrivifs.exception.ReglaNegocioException;
import cl.duoc.nutrivifs.producto.model.Producto;
import cl.duoc.nutrivifs.producto.service.ProductoService;
import cl.duoc.nutrivifs.usuario.model.Usuario;
import cl.duoc.nutrivifs.usuario.service.UsuarioService;
import cl.duoc.nutrivifs.util.Codigos;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Lógica de la compra: valida stock, descuenta stock, aplica el cupón y guarda la boleta
@Service
public class BoletaService {

    private static final Map<String, Double> CUPONES = Map.of("NUTRI10", 0.10);
    private static final List<String> ESTADOS = List.of("Entrega pendiente", "Listo", "Enviado");

    @Autowired
    private BoletaRepository boletaRepository;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private UsuarioService usuarioService;

    @Transactional   // si algo falla (por ejemplo, falta stock) se deshace toda la compra
    public BoletaDTO comprar(String mailCliente, BoletaRequest req){
        Usuario usuario = usuarioService.buscarPorMail(mailCliente);

        Boleta boleta = new Boleta();
        boleta.setFecha(LocalDateTime.now());
        boleta.setUsuario(usuario);
        boleta.setEstado("Entrega pendiente");

        int subtotal = 0;
        for (ItemRequest item : req.items()) {
            Producto producto = productoService.buscar(item.codigo());
            if (item.cantidad() > producto.getStock()) {
                throw new ReglaNegocioException("Stock insuficiente para " + producto.getNombre()
                        + ". Disponible: " + producto.getStock());
            }
            producto.setStock(producto.getStock() - item.cantidad());   // descuenta el stock

            DetalleBoleta detalle = new DetalleBoleta();
            detalle.setBoleta(boleta);
            detalle.setProducto(producto);
            detalle.setCantidad(item.cantidad());
            detalle.setPrecioUnitario(producto.getPrecio());            // el precio sale de la BD
            detalle.setSubtotal(producto.getPrecio() * item.cantidad());
            boleta.getDetalles().add(detalle);
            subtotal += detalle.getSubtotal();
        }

        String cupon = req.cupon() == null ? "" : req.cupon().trim().toUpperCase();
        if (!cupon.isEmpty() && !CUPONES.containsKey(cupon)) {
            throw new ReglaNegocioException("El cupón no existe o ya no está vigente.");
        }
        int descuento = (int) Math.round(subtotal * CUPONES.getOrDefault(cupon, 0.0));

        boleta.setSubtotal(subtotal);
        boleta.setDescuento(descuento);
        boleta.setTotal(subtotal - descuento);
        return aDTO(boletaRepository.save(boleta));
    }

    @Transactional(readOnly = true)
    public List<BoletaDTO> listar(){
        return boletaRepository.findAll().stream().map(this::aDTO).toList();
    }

    @Transactional(readOnly = true)
    public BoletaDTO buscarPorId(String codigo){
        return aDTO(buscar(codigo));
    }

    @Transactional(readOnly = true)
    public List<BoletaDTO> misBoletas(String mail){
        return boletaRepository.findByUsuario_MailOrderByFechaDesc(mail).stream().map(this::aDTO).toList();
    }

    @Transactional
    public BoletaDTO cambiarEstado(String codigo, String estado){
        if (!ESTADOS.contains(estado)) {
            throw new ReglaNegocioException("Estado inválido: " + estado + ". Usa: " + String.join(", ", ESTADOS));
        }
        Boleta boleta = buscar(codigo);
        boleta.setEstado(estado);
        return aDTO(boletaRepository.save(boleta));
    }

    // Acepta "ORD-0001" o "1"
    private Boleta buscar(String codigo){
        return boletaRepository.findById(Codigos.aId(codigo))
                .orElseThrow(() -> new NoEncontradoException("Boleta no encontrada: " + codigo));
    }

    private BoletaDTO aDTO(Boleta b){
        List<ItemBoletaDTO> items = b.getDetalles().stream()
                .map(d -> new ItemBoletaDTO(Codigos.formatear("ME", 3, d.getProducto().getIdProducto()),
                        d.getProducto().getNombre(), d.getPrecioUnitario(), d.getProducto().getImage(),
                        d.getCantidad()))
                .toList();
        Usuario u = b.getUsuario();
        return new BoletaDTO(Codigos.formatear("ORD-", 4, b.getId()), b.getFecha(),
                new ClienteDTO(u.getNombre() + " " + u.getApellido(), u.getMail()),
                items, b.getSubtotal(), b.getDescuento(), b.getTotal(), b.getEstado());
    }

}
