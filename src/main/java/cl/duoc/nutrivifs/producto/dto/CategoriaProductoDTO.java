package cl.duoc.nutrivifs.producto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Categoría de producto para el frontend: id = código "CAT01"
public record CategoriaProductoDTO(
        String id,
        @NotBlank @Size(max = 30) String nombre,
        @Size(max = 200) String descripcion) {
}
