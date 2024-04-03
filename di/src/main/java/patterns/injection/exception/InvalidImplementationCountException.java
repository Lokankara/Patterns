package patterns.injection.exception;

public class InvalidImplementationCountException
        extends RuntimeException {
    public InvalidImplementationCountException(String message) {
        super(message);
    }

    public InvalidImplementationCountException(
            String message,
            Throwable e) {
        super(message, e);
    }
}
