package cl.duoc.nutrivifs.resena.service;

import cl.duoc.nutrivifs.exception.NoEncontradoException;
import cl.duoc.nutrivifs.resena.dto.ResenaDTO;
import cl.duoc.nutrivifs.resena.dto.ResenaRequest;
import cl.duoc.nutrivifs.resena.model.Resena;
import cl.duoc.nutrivifs.resena.repository.ResenaRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResenaService {
    private final ResenaRepository repository;

    public ResenaService(ResenaRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<ResenaDTO> listar(String productoId) {
        return repository.findByProductoIdOrderByFechaDesc(productoId)
                .stream().map(ResenaDTO::desde).toList();
    }

    /** Una persona puede tener una reseña por producto; si ya existe, se actualiza. */
    @Transactional
    public ResenaDTO guardar(ResenaRequest request, String correo) {
        Resena resena = repository.findByProductoIdAndCorreo(request.productoId(), correo)
                .orElseGet(Resena::new);
        resena.setProductoId(request.productoId().trim());
        resena.setCorreo(correo);
        resena.setAutor(request.autor().trim());
        resena.setPuntuacion(request.puntuacion());
        resena.setComentario(request.comentario().trim());
        resena.setFecha(LocalDateTime.now());
        return ResenaDTO.desde(repository.save(resena));
    }

    @Transactional
    public void eliminar(String codigo, String correo, boolean administrador) {
        Long id = convertirId(codigo);
        Resena resena = repository.findById(id)
                .orElseThrow(() -> new NoEncontradoException("Reseña no encontrada: " + codigo));
        if (!administrador && !resena.getCorreo().equalsIgnoreCase(correo)) {
            throw new AccessDeniedException("Solo puedes eliminar tu propia reseña.");
        }
        repository.delete(resena);
    }

    private Long convertirId(String codigo) {
        try {
            String numero = codigo.toUpperCase().startsWith("RES-") ? codigo.substring(4) : codigo;
            return Long.parseLong(numero);
        } catch (NumberFormatException ex) {
            throw new NoEncontradoException("Código de reseña no válido: " + codigo);
        }
    }
}
