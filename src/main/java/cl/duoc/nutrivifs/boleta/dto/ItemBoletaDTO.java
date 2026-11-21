package cl.duoc.nutrivifs.boleta.dto;

// Producto dentro de una boleta (misma forma que ItemCarrito del frontend)
public record ItemBoletaDTO(String code, String name, Integer price, String image, Integer quantity) {
}
