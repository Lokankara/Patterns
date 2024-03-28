package patterns.web.repository.factory;

import org.springframework.stereotype.Repository;
import patterns.web.model.entity.Movie;

@Repository
public interface MovieDao
        extends Dao<Movie> {
}
