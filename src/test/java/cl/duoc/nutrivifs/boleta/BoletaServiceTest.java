package cl.duoc.nutrivifs.boleta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cl.duoc.nutrivifs.boleta.dto.BoletaDTO;
import cl.duoc.nutrivifs.boleta.dto.BoletaRequest;
import cl.duoc.nutrivifs.boleta.dto.ItemRequest;
import cl.duoc.nutrivifs.boleta.model.Boleta;
import cl.duoc.nutrivifs.boleta.repository.BoletaRepository;
import cl.duoc.nutrivifs.boleta.service.BoletaService;
import cl.duoc.nutrivifs.exception.ReglaNegocioException;
import cl.duoc.nutrivifs.producto.model.Producto;
import cl.duoc.nutrivifs.producto.service.ProductoService;
import cl.duoc.nutrivifs.usuario.model.Usuario;
import cl.duoc.nutrivifs.usuario.service.UsuarioService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

// Pruebas unitarias de la lógica de compra (sin base de datos: los repositorios se simulan con Mockito)
@ExtendWith(MockitoExtension.class)
class BoletaServiceTest {

    @Mock
    private BoletaRepository boletaRepository;

    @Mock
    private ProductoService productoService;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private BoletaService boletaService;

    private Usuario cliente;
    private Producto nexgard;

    @BeforeEach
    void preparar() {
        cliente = new Usuario();
        cliente.setId(1L);
        cliente.setNombre("Cliente");
        cliente.setApellido("Demo");
        cliente.setMail("cliente@gmail.com");

        nexgard = new Producto();
        nexgard.setIdProducto(4L);
        nexgard.setNombre("Nexgard");
        nexgard.setPrecio(9500);
        nexgard.setStock(5);

        when(usuarioService.buscarPorMail("cliente@gmail.com")).thenReturn(cliente);
        when(productoService.buscar("ME004")).thenReturn(nexgard);
    }

    @Test
    void noPermiteComprarMasQueElStock() {
        BoletaRequest compra = new BoletaRequest(List.of(new ItemRequest("ME004", 6)), null);

        assertThrows(ReglaNegocioException.class, () -> boletaService.comprar("cliente@gmail.com", compra));
        verify(boletaRepository, never()).save(any());
        assertEquals(5, nexgard.getStock());
    }

    @Test
    void aplicaCuponYDescuentaStock() {
        when(boletaRepository.save(any(Boleta.class))).thenAnswer(inv -> {
            Boleta b = inv.getArgument(0);
            b.setId(1L);
            return b;
        });
        BoletaRequest compra = new BoletaRequest(List.of(new ItemRequest("ME004", 2)), "nutri10");

        BoletaDTO boleta = boletaService.comprar("cliente@gmail.com", compra);

        assertEquals("ORD-0001", boleta.id());
        assertEquals(19000, boleta.subtotal());
        assertEquals(1900, boleta.discount());
        assertEquals(17100, boleta.total());
        assertEquals(3, nexgard.getStock());
    }

    @Test
    void rechazaCuponInexistente() {
        BoletaRequest compra = new BoletaRequest(List.of(new ItemRequest("ME004", 1)), "GRATIS");

        assertThrows(ReglaNegocioException.class, () -> boletaService.comprar("cliente@gmail.com", compra));
    }
}
