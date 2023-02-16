package ibn.api.health.api.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public abstract class BaseDTO implements Serializable {

    private String code;
}
