package cl.duoc.nutrivifs.boleta.dto;

import jakarta.validation.constraints.NotBlank;

// Nuevo estado de una boleta: Entrega pendiente, Listo o Enviado
public record EstadoRequest(@NotBlank String estado) {
}
