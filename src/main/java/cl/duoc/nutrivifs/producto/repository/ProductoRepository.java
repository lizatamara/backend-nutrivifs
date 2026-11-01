package cl.duoc.nutrivifs.producto.repository;

import cl.duoc.nutrivifs.producto.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}