package patterns.refactoring.exception;

public class GenreNotFoundException
        extends RuntimeException {
    public GenreNotFoundException(String message) {
        super(message);
    }
}