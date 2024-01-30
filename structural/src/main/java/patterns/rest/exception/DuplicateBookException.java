package patterns.rest.exception;

import lombok.AllArgsConstructor;
import patterns.rest.model.entity.Book;

@AllArgsConstructor
public class DuplicateBookException
        extends RuntimeException {

    private final Book book;
}
