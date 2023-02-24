package ibn.api.health.domain.service;

import ibn.api.health.domain.base.BaseService;
import ibn.api.health.domain.model.Historico;

import java.util.List;

public interface HistoricoService extends BaseService<Historico> {

    List<Historico> findAll(String code);
    Historico findUltimoHistorico(String code);
}
