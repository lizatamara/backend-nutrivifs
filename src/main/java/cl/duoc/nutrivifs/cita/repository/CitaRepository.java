package cl.duoc.nutrivifs.cita.repository;

import cl.duoc.nutrivifs.cita.model.Cita;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitaRepository extends JpaRepository<Cita, Long> {

    List<Cita> findAllByOrderByFechaSolicitudDesc();

    List<Cita> findByCorreoIgnoreCaseOrderByFechaSolicitudDesc(String correo);

    long countByFechaSolicitudBetween(LocalDateTime desde, LocalDateTime hasta);

    long countByEstadoAndFechaSolicitudBetween(String estado, LocalDateTime desde, LocalDateTime hasta);
}