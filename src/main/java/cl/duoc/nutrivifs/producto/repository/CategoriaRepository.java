package cl.duoc.nutrivifs.producto.repository;

import cl.duoc.nutrivifs.producto.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
