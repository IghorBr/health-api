package ibn.api.health.domain.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T extends BaseDomain> extends JpaRepository<T, Long> {

    Optional<T> findByCode(String code);
}
