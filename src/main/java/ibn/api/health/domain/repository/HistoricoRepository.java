package ibn.api.health.domain.repository;

import ibn.api.health.domain.base.BaseRepository;
import ibn.api.health.domain.model.Historico;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistoricoRepository extends BaseRepository<Historico> {

    @Query(value = "SELECT h FROM Historico h INNER JOIN h.usuario u WHERE u.code = ?1")
    List<Historico> findAll(String code);

    @Query(value = "SELECT h FROM Historico h INNER JOIN h.usuario u WHERE u.code = ?1 AND h.createdAt = (SELECT MAX(h.createdAt) FROM Historico h)")
    Optional<Historico> findUltimoHistorico(String code);
}
