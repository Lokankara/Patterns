package patterns.rest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import patterns.rest.model.entity.Book;
import patterns.rest.model.dto.BookResponse;
import patterns.rest.repository.BookRepository;
import patterns.rest.service.mapper.BookMapper;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JpaBookService
        implements BookService {

    private final BookMapper mapper;
    private final BookRepository repository;

    @Override
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        return ResponseEntity.ok(mapper.toListDto(repository.findAll()));
    }

    @Override
    public ResponseEntity<BookResponse> createBook(BookResponse dto) {
        Book newBookEntity = mapper.toEntity(dto);
        Book createdBook = repository.save(newBookEntity);
        return ResponseEntity.ok(mapper.toDto(createdBook));
    }

    @Override
    public ResponseEntity<BookResponse> getBookByTextNumber(String textNumber) {
        Optional<Book> optionalBook = repository.findById(textNumber);
        return optionalBook
                .map(book -> ResponseEntity.ok(mapper.toDto(book)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<BookResponse> updateBook(String textNumber, BookResponse dto) {
        return repository.findById(textNumber).map(book -> {
            book.setTextNumber(dto.getTextNumber());
            book.setType(dto.getType());
            book.setIssued(dto.getIssued());
            book.setTitle(dto.getTitle());
            book.setLanguage(dto.getLanguage());
            book.setAuthors(dto.getAuthors());
            book.setSubjects(dto.getSubjects());
            book.setLoCC(dto.getLoCC());
            book.setBookshelves(dto.getBookshelves());
            Book updatedBook = repository.save(book);
            return ResponseEntity.ok(mapper.toDto(updatedBook));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> deleteBook(String textNumber) {
        Optional<Book> optionalBook = repository.findById(textNumber);
        return optionalBook.map(existingBook -> {
            repository.delete(existingBook);
            return ResponseEntity.noContent().<Void>build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
