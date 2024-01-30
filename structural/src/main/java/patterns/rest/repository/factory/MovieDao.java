package patterns.rest.repository.factory;

import org.springframework.stereotype.Repository;
import patterns.rest.model.entity.Movie;

@Repository
public interface MovieDao
        extends Dao<Movie> {
}
