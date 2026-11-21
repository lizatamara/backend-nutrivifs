package cl.duoc.nutrivifs.cita.service;
import cl.duoc.nutrivifs.cita.dto.*; import cl.duoc.nutrivifs.cita.model.Cita; import cl.duoc.nutrivifs.cita.repository.CitaRepository;
import cl.duoc.nutrivifs.exception.NoEncontradoException;
import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate; import java.time.LocalDateTime; import java.util.List;
@Service
public class CitaService {
 private final CitaRepository repo; public CitaService(CitaRepository repo){this.repo=repo;}
 @Transactional public CitaDTO crear(CitaRequest r){
  try { if(LocalDate.parse(r.fecha()).isBefore(LocalDate.now())) throw new IllegalArgumentException("La fecha de la cita no puede estar en el pasado."); } catch(java.time.format.DateTimeParseException e){throw new IllegalArgumentException("La fecha debe tener formato AAAA-MM-DD.");}
  Cita c=new Cita(); c.setNombre(r.nombre().trim()); c.setRut(r.rut().trim()); c.setCorreo(r.correo().trim().toLowerCase()); c.setTelefono(r.telefono().trim()); c.setMascota(r.mascota().trim()); c.setEspecie(r.especie().trim()); c.setTipoConsulta(r.tipoConsulta().trim()); c.setFecha(r.fecha()); c.setMotivo(r.motivo().trim()); c.setEstado("Pendiente"); c.setFechaSolicitud(LocalDateTime.now()); return CitaDTO.desde(repo.save(c));
 }
 public List<CitaDTO> listar(){return repo.findAllByOrderByFechaSolicitudDesc().stream().map(CitaDTO::desde).toList();}
 public List<CitaDTO> mias(String correo){return repo.findByCorreoIgnoreCaseOrderByFechaSolicitudDesc(correo).stream().map(CitaDTO::desde).toList();}
 @Transactional public CitaDTO cambiarEstado(String codigo,String estado){if(!List.of("Pendiente","Confirmada","Rechazada").contains(estado)) throw new IllegalArgumentException("Estado no válido."); Cita c=buscar(codigo); c.setEstado(estado); return CitaDTO.desde(repo.save(c));}
 private Cita buscar(String codigo){try{String n=codigo.toUpperCase().startsWith("CIT-")?codigo.substring(4):codigo; return repo.findById(Long.parseLong(n)).orElseThrow(()->new NoEncontradoException("Cita no encontrada: "+codigo));}catch(NumberFormatException e){throw new NoEncontradoException("Código de cita no válido: "+codigo);}}
}
