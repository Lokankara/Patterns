package patterns.rest.service.mapper;

import org.springframework.stereotype.Component;
import patterns.rest.model.entity.Book;
import patterns.rest.model.dto.BookResponse;

import java.util.List;

@Component
public class BookMapper {

    public Book toEntity(BookResponse dto) {
        if (dto == null) {
            return null;
        }

        return Book.builder()
                   .textNumber(dto.getTextNumber())
                   .type(dto.getType())
                   .issued(dto.getIssued())
                   .title(dto.getTitle())
                   .language(dto.getLanguage())
                   .authors(dto.getAuthors())
                   .subjects(dto.getSubjects())
                   .loCC(dto.getLoCC())
                   .bookshelves(dto.getBookshelves())
                   .build();
    }

    public List<BookResponse> toListDto(List<Book> bookEntities) {
        return bookEntities.stream().map(this::toDto).toList();
    }

    public BookResponse toDto(Book book) {
        return BookResponse
                .builder()
                .textNumber(book.getTextNumber())
                .type(book.getType())
                .issued(book.getIssued())
                .title(book.getTitle())
                .language(book.getLanguage())
                .authors(book.getAuthors())
                .subjects(book.getSubjects())
                .loCC(book.getLoCC())
                .bookshelves(book.getBookshelves())
                .build();
    }
}
