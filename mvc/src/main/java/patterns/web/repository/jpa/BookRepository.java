package patterns.web.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import patterns.web.model.entity.Book;

public interface BookRepository
        extends JpaRepository<Book, String> {
}
