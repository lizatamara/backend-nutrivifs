package cl.duoc.nutrivifs.cita.dto;
import jakarta.validation.constraints.*;
public record CitaRequest(
 @NotBlank @Size(max=120) String nombre,
 @NotBlank @Size(max=20) String rut,
 @NotBlank @Email @Size(max=120) String correo,
 @NotBlank @Size(max=30) String telefono,
 @NotBlank @Size(max=100) String mascota,
 @NotBlank @Size(max=60) String especie,
 @NotBlank @Size(max=120) String tipoConsulta,
 @NotBlank @Size(max=10) String fecha,
 @NotBlank @Size(min=20,max=1000) String motivo
) {}
