package ibn.api.health.domain.exception;

public class HealthException extends RuntimeException {

    public HealthException(String message) {
        super(message);
    }

    public HealthException(String message, Throwable cause) {
        super(message, cause);
    }
}
