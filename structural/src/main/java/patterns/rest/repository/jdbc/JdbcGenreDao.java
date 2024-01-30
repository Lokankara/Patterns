package patterns.rest.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import patterns.rest.model.entity.Genre;
import patterns.rest.repository.factory.GenreDao;
import patterns.rest.repository.template.JdbcGenreTemplate;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcGenreDao
        implements GenreDao {

    private final JdbcGenreTemplate template;

    @Override
    public Genre save(Genre genre) {
        return template.save(genre);
    }

    @Override
    public List<Genre> saveAll(List<Genre> list) {
        return template.saveAll(list);
    }

    @Override
    public Optional<Genre> findBy(String name) {
        return template.getByName(name);
    }

    @Override
    public List<Genre> findAll() {
        return template.getAll();
    }
}
