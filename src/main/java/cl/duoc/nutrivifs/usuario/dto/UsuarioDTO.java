package cl.duoc.nutrivifs.usuario.dto;

import java.time.LocalDate;

// Lo que la API devuelve de un usuario (nunca incluye la contraseña).
// Mismos nombres que la interfaz Perfil/Sesion del frontend.
public record UsuarioDTO(
        Long id,
        String run,
        String name,
        String apellido,
        String email,
        String role,
        LocalDate birthDate,
        String direccion,
        String region,
        String comuna,
        String phone,
        boolean locked) {
}
