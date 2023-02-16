package ibn.api.health.api.mapper;

import ibn.api.health.api.model.out.UsuarioDTO;
import ibn.api.health.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper extends BaseMapper<Usuario, UsuarioDTO> {

    protected UsuarioMapper(ModelMapper mapper) {
        super(Usuario.class, UsuarioDTO.class, mapper);
    }
}
