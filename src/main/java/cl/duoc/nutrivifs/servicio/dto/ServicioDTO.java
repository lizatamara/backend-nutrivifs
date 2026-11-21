package cl.duoc.nutrivifs.servicio.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// Servicio de la clínica para el frontend: codigo = "SE001", categoria = nombre de la categoría
public record ServicioDTO(
        String codigo,
        @NotBlank @Size(max = 100) String nombre,
        @Size(max = 255) String descripcion,
        String especie,
        @NotNull @Min(1) Integer duracion,      // minutos
        @NotNull @Min(0) Integer precio,
        String observacion,
        String imagen,
        String alt,
        String consulta,
        boolean enlace,
        @NotBlank String categoria) {
}
