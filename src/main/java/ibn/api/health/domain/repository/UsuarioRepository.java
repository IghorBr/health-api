package ibn.api.health.domain.repository;

import ibn.api.health.domain.base.BaseRepository;
import ibn.api.health.domain.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario> {

    Optional<Usuario> findByEmail(String email);
}
