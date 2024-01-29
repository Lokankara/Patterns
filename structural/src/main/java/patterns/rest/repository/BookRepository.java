package patterns.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import patterns.rest.model.entity.Book;

public interface BookRepository
        extends JpaRepository<Book, String> {
}
