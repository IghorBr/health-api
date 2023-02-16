package ibn.api.health.core.security.auth_server;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class AuthUser extends User {

    private final String code;
    private final Long id;
    private final String role;

    public AuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities, String code, Long id, String role) {
        super(username, password, authorities);
        this.code = code;
        this.id = id;
        this.role = role;
    }
}
