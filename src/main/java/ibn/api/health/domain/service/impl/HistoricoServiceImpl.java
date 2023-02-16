package ibn.api.health.domain.service.impl;

import ibn.api.health.domain.base.BaseServiceImpl;
import ibn.api.health.domain.model.Historico;
import ibn.api.health.domain.repository.HistoricoRepository;
import ibn.api.health.domain.service.HistoricoService;
import org.springframework.stereotype.Service;

@Service
public class HistoricoServiceImpl extends BaseServiceImpl<Historico> implements HistoricoService {

    private final HistoricoRepository historicoRepository;

    protected HistoricoServiceImpl(HistoricoRepository historicoRepository) {
        super(historicoRepository, Historico.class);
        this.historicoRepository = historicoRepository;
    }
}