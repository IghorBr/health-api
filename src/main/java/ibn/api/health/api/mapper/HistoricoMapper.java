package ibn.api.health.api.mapper;

import ibn.api.health.api.model.out.HistoricoDTO;
import ibn.api.health.domain.model.Historico;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class HistoricoMapper extends BaseMapper<Historico, HistoricoDTO> {

    protected HistoricoMapper(ModelMapper mapper) {
        super(Historico.class, HistoricoDTO.class, mapper);
    }
}
