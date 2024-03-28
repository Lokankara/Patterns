package patterns.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import patterns.web.model.dto.BookResponse;
import patterns.web.model.entity.Book;
import patterns.web.repository.jpa.BookRepository;
import patterns.web.service.mapper.BookMapper;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JpaBookService
        implements BookService {

    private final BookMapper mapper;
    private final BookRepository repository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Page<BookResponse>> getAllBooks(Pageable pageable) {
        return ResponseEntity.ok(repository.findAll(pageable).map(mapper::toDto));
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
