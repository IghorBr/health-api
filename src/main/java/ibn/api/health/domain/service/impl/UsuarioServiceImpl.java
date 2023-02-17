package ibn.api.health.domain.service.impl;

import ibn.api.health.core.security.HealthSecurity;
import ibn.api.health.domain.base.BaseServiceImpl;
import ibn.api.health.domain.model.Usuario;
import ibn.api.health.domain.service.UsuarioService;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario> implements UsuarioService {

    private final ibn.api.health.domain.repository.UsuarioRepository usuarioRepository;

    protected UsuarioServiceImpl(ibn.api.health.domain.repository.UsuarioRepository usuarioRepository, HealthSecurity security) {
        super(usuarioRepository, Usuario.class, security);
        this.usuarioRepository = usuarioRepository;
    }
}
