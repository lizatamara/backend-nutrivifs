package cl.duoc.nutrivifs.exception;

import java.time.LocalDateTime;

// Respuesta de error uniforme: el frontend muestra el campo "mensaje"
public record ErrorDTO(int status, String mensaje, LocalDateTime fecha) {
}
