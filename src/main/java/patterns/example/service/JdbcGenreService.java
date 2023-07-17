package patterns.example.service;

import patterns.example.dao.GenreDao;
import patterns.example.dao.jdbc.JdbcGenreDao;
import patterns.example.model.entity.Genre;

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
