package cl.duoc.nutrivifs.resena.dto;
import jakarta.validation.constraints.*;
public record ResenaRequest(@NotBlank @Size(max=30) String productoId,@NotBlank @Size(max=100) String autor,@Min(1) @Max(5) int puntuacion,@NotBlank @Size(min=10,max=300) String comentario) {}
