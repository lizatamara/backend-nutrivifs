package cl.duoc.nutrivifs.auth.dto;

import cl.duoc.nutrivifs.usuario.dto.UsuarioDTO;

// Respuesta del login y de la renovación: el token JWT y los datos del usuario
public record AuthResponse(String token, UsuarioDTO usuario) {
}
