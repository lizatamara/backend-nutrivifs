package cl.duoc.nutrivifs.mensaje.dto;
import jakarta.validation.constraints.*;
public record MensajeRequest(@NotBlank @Size(max=100) String nombre,@NotBlank @Email @Size(max=100) String correo,@NotBlank @Size(max=30) String motivo,@NotBlank @Size(max=500) String comentario) {}
