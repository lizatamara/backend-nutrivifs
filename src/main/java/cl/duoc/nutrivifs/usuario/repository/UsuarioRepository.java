package cl.duoc.nutrivifs.usuario.repository;

import cl.duoc.nutrivifs.usuario.model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByMail(String mail);

    boolean existsByMail(String mail);

    boolean existsByRun(String run);
}
