package cl.duoc.nutrivifs.boleta.repository;

import cl.duoc.nutrivifs.boleta.model.Boleta;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoletaRepository extends JpaRepository<Boleta, Long> {

    List<Boleta> findByUsuario_MailOrderByFechaDesc(String mail);

    boolean existsByUsuario_Id(Long idUsuario);

    long countByFechaBetween(LocalDateTime desde, LocalDateTime hasta);

    @Query("select coalesce(sum(b.total), 0) from Boleta b where b.fecha between :desde and :hasta")
    long sumarTotalEntre(@Param("desde") LocalDateTime desde, @Param("hasta") LocalDateTime hasta);
}