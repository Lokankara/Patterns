package patterns.rest.service.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patterns.rest.model.entity.Genre;
import patterns.rest.repository.factory.GenreDao;
import patterns.rest.service.GenreService;

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
