package patterns.web.repository.factory;

import org.springframework.stereotype.Repository;
import patterns.web.model.entity.Genre;

@Repository
public interface GenreDao
        extends Dao<Genre> {
}
