package patterns.service;

import patterns.dao.GenreDao;
import patterns.dao.jdbc.JdbcGenreDao;
import patterns.model.entity.Genre;

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
