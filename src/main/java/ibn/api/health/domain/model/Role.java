package ibn.api.health.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    GOD_LIKE("ROLE_GOD_LIKE");

    private final String descricao;
}
