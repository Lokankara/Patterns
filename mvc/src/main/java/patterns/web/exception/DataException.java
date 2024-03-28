package patterns.web.exception;

public class DataException
        extends RuntimeException {
    public DataException(String message) {
        super(message);
    }
}
