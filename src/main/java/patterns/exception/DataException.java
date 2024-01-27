package patterns.exception;

public class DataException
        extends RuntimeException {
    public DataException(String message) {
        super(message);
    }
}
