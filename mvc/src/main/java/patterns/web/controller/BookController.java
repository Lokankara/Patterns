package patterns.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import patterns.web.model.dto.BookResponse;
import patterns.web.service.BookService;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<Page<BookResponse>> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return bookService.getAllBooks(pageRequest);
    }

    @GetMapping("/{textNumber}")
    public ResponseEntity<BookResponse> getBookByTextNumber(
            @PathVariable String textNumber) {
        return bookService.getBookByTextNumber(textNumber);
    }

    @PostMapping
    public ResponseEntity<BookResponse> createBook(
            @RequestBody BookResponse bookDTO) {
        return bookService.createBook(bookDTO);
    }

    @PutMapping("/{textNumber}")
    public ResponseEntity<BookResponse> updateBook(
            @PathVariable String textNumber,
            @RequestBody BookResponse dto) {
        return bookService.updateBook(textNumber, dto);
    }

    @DeleteMapping("/{textNumber}")
    public ResponseEntity<Void> deleteBook(
            @PathVariable String textNumber) {
        return bookService.deleteBook(textNumber);
    }
}
