package patterns.web.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import patterns.web.model.entity.Genre;
import patterns.web.repository.factory.GenreDao;
import patterns.web.repository.template.JdbcGenreTemplate;

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
