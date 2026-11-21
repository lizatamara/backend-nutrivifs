package cl.duoc.nutrivifs.boleta.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// Una línea de la compra: el frontend solo envía el código y la cantidad (el precio lo pone el backend)
public record ItemRequest(
        @NotBlank String codigo,
        @NotNull @Min(1) Integer cantidad) {
}
