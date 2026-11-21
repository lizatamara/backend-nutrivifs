package cl.duoc.nutrivifs.producto.repository;

import cl.duoc.nutrivifs.producto.model.CategoriaProducto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaProductoRepository extends JpaRepository<CategoriaProducto, Long> {

    Optional<CategoriaProducto> findByNombre(String nombre);

    boolean existsByNombreIgnoreCase(String nombre);
}
