package patterns.web.service.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patterns.web.model.entity.Genre;
import patterns.web.repository.factory.GenreDao;
import patterns.web.service.GenreService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JdbcGenreService implements GenreService {

    private final GenreDao genreDao;

    @Override
    public List<Genre> saveAll(List<Genre> genres) {
        genres.forEach(genre -> genre.setGenreId(
                UUID.randomUUID().getMostSignificantBits()));
        return genreDao.saveAll(genres);
    }
}
