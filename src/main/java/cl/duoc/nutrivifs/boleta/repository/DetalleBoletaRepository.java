package cl.duoc.nutrivifs.boleta.repository;

import cl.duoc.nutrivifs.boleta.model.DetalleBoleta;
import cl.duoc.nutrivifs.reporte.dto.ProductoTopDTO;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleBoletaRepository extends JpaRepository<DetalleBoleta, Long> {

    boolean existsByProducto_IdProducto(Long idProducto);

    @Query("select coalesce(sum(d.cantidad), 0) from DetalleBoleta d " +
            "where d.boleta.fecha between :desde and :hasta")
    long sumarUnidadesEntre(@Param("desde") LocalDateTime desde, @Param("hasta") LocalDateTime hasta);

    @Query("select new cl.duoc.nutrivifs.reporte.dto.ProductoTopDTO(d.producto.nombre, sum(d.cantidad)) " +
            "from DetalleBoleta d where d.boleta.fecha between :desde and :hasta " +
            "group by d.producto.nombre order by sum(d.cantidad) desc")
    List<ProductoTopDTO> masVendidosEntre(@Param("desde") LocalDateTime desde,
                                          @Param("hasta") LocalDateTime hasta,
                                          Pageable pageable);
}