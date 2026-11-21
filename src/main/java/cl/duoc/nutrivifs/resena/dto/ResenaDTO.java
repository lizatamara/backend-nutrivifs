package cl.duoc.nutrivifs.resena.dto;

import cl.duoc.nutrivifs.resena.model.Resena;

/** Respuesta con los mismos nombres que espera la interfaz Resena de React. */
public record ResenaDTO(
        String id,
        String productoId,
        String email,
        String autor,
        int puntuacion,
        String comentario,
        String fecha
) {
    public static ResenaDTO desde(Resena resena) {
        return new ResenaDTO(
                "RES-" + resena.getId(),
                resena.getProductoId(),
                resena.getCorreo(),
                resena.getAutor(),
                resena.getPuntuacion(),
                resena.getComentario(),
                resena.getFecha().toString()
        );
    }
}
