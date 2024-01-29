package patterns.rest.repository.dao.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import patterns.rest.model.entity.Genre;
import patterns.rest.repository.dao.factory.GenreDao;
import patterns.rest.repository.dao.template.JdbcGenreTemplate;

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
