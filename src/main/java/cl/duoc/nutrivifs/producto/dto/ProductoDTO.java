package cl.duoc.nutrivifs.producto.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// Producto tal como lo usa el frontend (interfaz Producto de tipos.ts).
// id = código "ME001" generado desde el id de la BD; category = nombre de la categoría.
public record ProductoDTO(
        String id,
        @NotBlank @Size(max = 100) String name,
        @Size(max = 255) String description,
        String species,
        @NotNull @Min(0) Integer price,
        @NotNull @Min(0) Integer stock,
        @Min(0) Integer stockCritico,
        @NotBlank String category,
        String image) {
}
