package patterns.rest.service;

import org.springframework.http.ResponseEntity;
import patterns.rest.model.dto.BookResponse;

import java.util.List;

public interface BookService {
    ResponseEntity<List<BookResponse>> getAllBooks();

    ResponseEntity<BookResponse> getBookByTextNumber(String textNumber);

    ResponseEntity<BookResponse> updateBook(String textNumber, BookResponse dto);

    ResponseEntity<BookResponse> createBook(BookResponse dto);

    ResponseEntity<Void> deleteBook(String textNumber);
}
