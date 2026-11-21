package cl.duoc.nutrivifs.producto.service;

import cl.duoc.nutrivifs.boleta.repository.DetalleBoletaRepository;
import cl.duoc.nutrivifs.exception.NoEncontradoException;
import cl.duoc.nutrivifs.exception.ReglaNegocioException;
import cl.duoc.nutrivifs.producto.dto.ProductoDTO;
import cl.duoc.nutrivifs.producto.model.CategoriaProducto;
import cl.duoc.nutrivifs.producto.model.Producto;
import cl.duoc.nutrivifs.producto.repository.CategoriaProductoRepository;
import cl.duoc.nutrivifs.producto.repository.ProductoRepository;
import cl.duoc.nutrivifs.util.Codigos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaProductoRepository categoriaProductoRepository;

    @Autowired
    private DetalleBoletaRepository detalleBoletaRepository;

    public List<ProductoDTO> listar(){
        return productoRepository.findAll().stream().map(this::aDTO).toList();
    }

    public ProductoDTO buscarPorId(String codigo){
        return aDTO(buscar(codigo));
    }

    public ProductoDTO registrar(ProductoDTO dto){
        Producto producto = new Producto();
        copiar(dto, producto);
        return aDTO(productoRepository.save(producto));
    }

    public ProductoDTO actualizar(String codigo, ProductoDTO dto){
        Producto producto = buscar(codigo);
        copiar(dto, producto);
        return aDTO(productoRepository.save(producto));
    }

    // No se borra un producto que ya aparece en boletas: se perdería el historial de ventas
    public void eliminar(String codigo){
        Producto producto = buscar(codigo);
        if (detalleBoletaRepository.existsByProducto_IdProducto(producto.getIdProducto())) {
            throw new ReglaNegocioException("No se puede eliminar: el producto aparece en boletas emitidas.");
        }
        productoRepository.delete(producto);
    }

    // También lo usa BoletaService para validar y descontar stock
    public Producto buscar(String codigo){
        return productoRepository.findById(Codigos.aId(codigo))
                .orElseThrow(() -> new NoEncontradoException("Producto no encontrado: " + codigo));
    }

    private void copiar(ProductoDTO dto, Producto producto){
        CategoriaProducto categoria = categoriaProductoRepository.findByNombre(dto.category())
                .orElseThrow(() -> new ReglaNegocioException("La categoría no existe: " + dto.category()));
        producto.setNombre(dto.name().trim());
        producto.setDescripcion(dto.description());
        producto.setEspecies(dto.species());
        producto.setPrecio(dto.price());
        producto.setStock(dto.stock());
        producto.setStockCritico(dto.stockCritico() == null ? 0 : dto.stockCritico());
        producto.setImage(dto.image());
        producto.setCategoria(categoria);
    }

    public ProductoDTO aDTO(Producto p){
        String categoria = p.getCategoria() == null ? null : p.getCategoria().getNombre();
        return new ProductoDTO(Codigos.formatear("ME", 3, p.getIdProducto()), p.getNombre(),
                p.getDescripcion(), p.getEspecies(), p.getPrecio(), p.getStock(), p.getStockCritico(),
                categoria, p.getImage());
    }

}
