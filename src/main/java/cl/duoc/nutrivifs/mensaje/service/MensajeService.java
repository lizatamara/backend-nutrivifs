package cl.duoc.nutrivifs.mensaje.service;
import cl.duoc.nutrivifs.mensaje.dto.MensajeRequest; import cl.duoc.nutrivifs.mensaje.model.Mensaje; import cl.duoc.nutrivifs.mensaje.repository.MensajeRepository;
import org.springframework.stereotype.Service; import java.time.LocalDateTime; import java.util.List;
@Service public class MensajeService {
 private final MensajeRepository repo; public MensajeService(MensajeRepository repo){this.repo=repo;}
 public Mensaje crear(MensajeRequest r){Mensaje m=new Mensaje();m.setNombre(r.nombre().trim());m.setCorreo(r.correo().trim().toLowerCase());m.setMotivo(r.motivo());m.setComentario(r.comentario().trim());m.setFecha(LocalDateTime.now());return repo.save(m);}
 public List<Mensaje> listar(){return repo.findAllByOrderByFechaDesc();} public void eliminar(Long id){if(!repo.existsById(id))throw new IllegalArgumentException("Mensaje no encontrado.");repo.deleteById(id);}
}
