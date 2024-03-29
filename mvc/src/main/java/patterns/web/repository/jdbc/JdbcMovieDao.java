package patterns.web.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import patterns.web.model.entity.Movie;
import patterns.web.repository.factory.MovieDao;
import patterns.web.repository.template.JdbcMovieTemplate;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcMovieDao
        implements MovieDao {

    private final JdbcMovieTemplate template;

    @Override
    public Movie save(Movie movie) {
        return template.save(movie);
    }

    @Override
    public List<Movie> saveAll(List<Movie> movies) {
        return template.saveAll(movies);
    }

    @Override
    public Optional<Movie> findBy(String title) {
        return template.getByName(title);
    }

    @Override
    public List<Movie> findAll() {
        return template.getAll();
    }
}
