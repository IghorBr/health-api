package ibn.api.health.core.security;

import ibn.api.health.core.security.auth_server.AuthUser;
import ibn.api.health.domain.exception.ObjectNotFoundException;
import ibn.api.health.domain.model.Usuario;
import ibn.api.health.domain.repository.UsuarioRepository;
import ibn.api.health.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
public class HealthSecurity {

    private final UsuarioRepository usuarioRepository;

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Jwt getPrincipal() {
        return (Jwt) this.getAuthentication().getPrincipal();
    }

    public String getCode() {
        Jwt principal = (Jwt) this.getAuthentication().getPrincipal();
        return principal.getClaim("code");
    }

    public String getRole() {
        Jwt principal = (Jwt) this.getAuthentication().getPrincipal();
        return principal.getClaim("role");
    }

    public Long getId() {
        Jwt principal = (Jwt) this.getAuthentication().getPrincipal();
        return principal.getClaim("usuario_id");
    }

    public Usuario getUsuarioLogado() {
        return usuarioRepository.findById(getId()).orElseThrow(() -> new ObjectNotFoundException(String.format("Usuário de id %d não encontrado!", getId())));
    }

    private boolean hasAuthority(String authorityName) {
        return getAuthentication().getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(authorityName));
    }

    public boolean isAdmin() {
        return hasAuthority("ROLE_ADMIN");
    }

    public boolean isUser() {
        return hasAuthority("ROLE_USER");
    }

    public boolean isGodLike() {
        return hasAuthority("ROLE_GOD_LIKE");
    }

    public void verifyCode(String code) {
        if (!isGodLike() || !(StringUtils.hasText(code) && getCode().equals(code)))
            throw new AccessDeniedException("Acesso Negado");
    }
}
