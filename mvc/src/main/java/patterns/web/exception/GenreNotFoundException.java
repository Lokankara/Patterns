package patterns.web.exception;

public class GenreNotFoundException
        extends RuntimeException {
    public GenreNotFoundException(String message) {
        super(message);
    }
}
