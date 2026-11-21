package cl.duoc.nutrivifs.mensaje.repository;
import cl.duoc.nutrivifs.mensaje.model.Mensaje; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface MensajeRepository extends JpaRepository<Mensaje,Long>{List<Mensaje> findAllByOrderByFechaDesc();}
