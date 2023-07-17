package patterns.refactoring.service;

import patterns.refactoring.dao.GenreDao;
import patterns.refactoring.dao.jdbc.JdbcGenreDao;
import patterns.refactoring.model.entity.Genre;

import java.util.List;
import java.util.UUID;

public class JdbcGenreService {
    private final GenreDao genreDao;

    public JdbcGenreService() {
        genreDao = new JdbcGenreDao();
    }

    public List<Genre> saveAll(List<Genre> genres) {
        genres.forEach(genre -> genre.setGenreId(
                UUID.randomUUID().getMostSignificantBits()));
        return genreDao.saveAll(genres);
    }
}
