package cl.duoc.nutrivifs.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

// Datos que llegan para registrar, crear o editar un usuario
public record UsuarioRequest(
        @NotBlank
        @Pattern(regexp = "^\\d{6,8}[0-9Kk]$", message = "RUN sin puntos ni guion, ej: 19011022K")
        String run,
        @NotBlank @Size(max = 50) String name,
        @NotBlank @Size(max = 100) String apellido,
        @NotBlank @Email
        @Pattern(regexp = "^[^@]+@(duoc\\.cl|profesor\\.duoc\\.cl|gmail\\.com)$",
                 message = "Solo correos @duoc.cl, @profesor.duoc.cl o @gmail.com")
        String email,
        String password,      // obligatoria al crear; vacía al editar = no se cambia
        String role,          // solo lo usa el Administrador
        LocalDate birthDate,
        @NotBlank @Size(max = 300) String direccion,
        @NotBlank String region,
        @NotBlank String comuna,
        String phone) {
}
