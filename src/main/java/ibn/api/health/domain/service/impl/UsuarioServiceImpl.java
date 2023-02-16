package ibn.api.health.domain.service.impl;

import ibn.api.health.domain.base.BaseRepository;
import ibn.api.health.domain.base.BaseServiceImpl;
import ibn.api.health.domain.model.Usuario;
import ibn.api.health.domain.repository.UsuarioRepository;
import ibn.api.health.domain.service.UsuarioService;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario> implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    protected UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        super(usuarioRepository, Usuario.class);
        this.usuarioRepository = usuarioRepository;
    }
}
