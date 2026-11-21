package cl.duoc.nutrivifs.resena.repository;
import cl.duoc.nutrivifs.resena.model.Resena; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List; import java.util.Optional;
public interface ResenaRepository extends JpaRepository<Resena,Long>{List<Resena> findByProductoIdOrderByFechaDesc(String productoId); Optional<Resena> findByProductoIdAndCorreo(String productoId,String correo);}
