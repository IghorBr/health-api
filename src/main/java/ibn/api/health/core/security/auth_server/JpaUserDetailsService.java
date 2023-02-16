package ibn.api.health.core.security.auth_server;

import ibn.api.health.domain.model.Usuario;
import ibn.api.health.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(usuario.getRole().getDescricao());

        return new AuthUser(usuario.getEmail(), usuario.getSenha(), Arrays.asList(authority), usuario.getCode(), usuario.getId(), usuario.getRole().getDescricao());
    }
}
