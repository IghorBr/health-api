package ibn.api.health.api.mapper;

import ibn.api.health.api.model.input.HistoricoInput;
import ibn.api.health.api.model.out.HistoricoDTO;
import ibn.api.health.domain.model.Historico;
import ibn.api.health.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class HistoricoMapper extends BaseMapper<Historico, HistoricoDTO> {

    protected HistoricoMapper(ModelMapper mapper) {
        super(Historico.class, HistoricoDTO.class, mapper);
    }

    public Historico inputToDomain(Usuario usuario, HistoricoInput input) {
        return new Historico(
                input.getObservacoes(),
                input.getPressaoSistolica(),
                input.getPressaoDiastolica(),
                input.getBpm(),
                input.getGlicose(),
                input.getAltura(),
                input.getPeso(),
                usuario
        );
    }
}
