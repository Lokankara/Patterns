package patterns.refactoring.dao.jdbc;

import lombok.RequiredArgsConstructor;
import patterns.refactoring.dao.MovieDao;
import patterns.refactoring.dao.template.JdbcMovieTemplate;
import patterns.refactoring.model.entity.Movie;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class JdbcMovieDao
        implements MovieDao {

    private final JdbcMovieTemplate template;

    public JdbcMovieDao() {
        this.template = new JdbcMovieTemplate();
    }

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
