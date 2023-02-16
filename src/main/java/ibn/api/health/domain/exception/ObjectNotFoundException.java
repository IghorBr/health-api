package ibn.api.health.domain.exception;

public class ObjectNotFoundException extends HealthException {
    public ObjectNotFoundException(String message) {
        super(message);
    }

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
