package ibn.api.health.domain.service.impl;

import ibn.api.health.core.security.HealthSecurity;
import ibn.api.health.domain.base.BaseServiceImpl;
import ibn.api.health.domain.exception.ObjectNotFoundException;
import ibn.api.health.domain.model.Historico;
import ibn.api.health.domain.repository.HistoricoRepository;
import ibn.api.health.domain.service.HistoricoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricoServiceImpl extends BaseServiceImpl<Historico> implements HistoricoService {

    private final HistoricoRepository historicoRepository;

    protected HistoricoServiceImpl(HistoricoRepository historicoRepository, HealthSecurity security) {
        super(historicoRepository, Historico.class, security);
        this.historicoRepository = historicoRepository;
    }

    @Override
    public List<Historico> findAll(String code) {
        return historicoRepository.findAll(code);
    }

    @Override
    public Historico findUltimoHistorico(String code) {
        return historicoRepository.findUltimoHistorico(code).orElseThrow(() -> new ObjectNotFoundException("Historico nao encontrado"));
    }
}
