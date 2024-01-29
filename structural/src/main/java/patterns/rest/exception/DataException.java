package patterns.rest.exception;

public class DataException
        extends RuntimeException {
    public DataException(String message) {
        super(message);
    }
}
