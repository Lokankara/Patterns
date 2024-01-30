package patterns.rest.repository.factory;

import org.springframework.stereotype.Repository;
import patterns.rest.model.entity.Genre;

@Repository
public interface GenreDao
        extends Dao<Genre> {
}
