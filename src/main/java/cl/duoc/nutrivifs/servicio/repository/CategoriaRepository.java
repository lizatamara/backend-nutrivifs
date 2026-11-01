package cl.duoc.nutrivifs.servicio.repository;

import cl.duoc.nutrivifs.servicio.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
