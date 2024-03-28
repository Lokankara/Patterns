package patterns.web.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import patterns.web.model.dto.BookResponse;

public interface BookService {
    ResponseEntity<Page<BookResponse>> getAllBooks(Pageable pageable);

    ResponseEntity<BookResponse> getBookByTextNumber(String textNumber);

    ResponseEntity<BookResponse> updateBook(String textNumber, BookResponse dto);

    ResponseEntity<BookResponse> createBook(BookResponse dto);

    ResponseEntity<Void> deleteBook(String textNumber);
}
