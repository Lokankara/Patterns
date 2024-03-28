package patterns.web.exception;

import lombok.AllArgsConstructor;
import patterns.web.model.entity.Book;

@AllArgsConstructor
public class DuplicateBookException
        extends RuntimeException {

    private final Book book;
}
