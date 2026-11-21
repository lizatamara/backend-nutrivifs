package cl.duoc.nutrivifs.cita.dto;
import cl.duoc.nutrivifs.cita.model.Cita;
import java.time.format.DateTimeFormatter;
public record CitaDTO(String id,String fechaSolicitud,String nombre,String rut,String correo,String telefono,String mascota,String especie,String tipoConsulta,String fecha,String motivo,String estado) {
 public static CitaDTO desde(Cita c) { return new CitaDTO("CIT-"+String.format("%04d",c.getId()), c.getFechaSolicitud().toString(), c.getNombre(),c.getRut(),c.getCorreo(),c.getTelefono(),c.getMascota(),c.getEspecie(),c.getTipoConsulta(),c.getFecha(),c.getMotivo(),c.getEstado()); }
}
