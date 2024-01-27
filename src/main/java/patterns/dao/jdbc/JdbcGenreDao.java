package patterns.dao.jdbc;

import patterns.dao.GenreDao;
import patterns.dao.template.JdbcGenreTemplate;
import patterns.model.entity.Genre;

import java.util.List;
import java.util.Optional;

public class JdbcGenreDao
        implements GenreDao {

    private final JdbcGenreTemplate template;

    public JdbcGenreDao() {
        this.template = new JdbcGenreTemplate();
    }

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
