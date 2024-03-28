package patterns.web.service.jdbc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import patterns.web.exception.UserNotFoundException;
import patterns.web.model.dto.MovieDetailsResponse;
import patterns.web.model.dto.MovieRequest;
import patterns.web.model.dto.MovieResponse;
import patterns.web.model.dto.MovieSearchResponse;
import patterns.web.model.entity.Genre;
import patterns.web.model.entity.Movie;
import patterns.web.model.entity.MovieDetails;
import patterns.web.model.entity.Review;
import patterns.web.repository.factory.MovieDao;
import patterns.web.repository.jpa.MovieDetailsDao;
import patterns.web.service.MovieService;
import patterns.web.service.mapper.MovieMapper;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static java.util.Collections.singletonList;

@Slf4j
@Service
@RequiredArgsConstructor
public class JdbcMovieService
        implements MovieService {

    @Value("${api.baseApi}")
    private String baseApi;
    @Value("${api.key}")
    private String apiKey;
    private final MovieDao movieDao;
    private final MovieMapper mapper;
    private final MovieDetailsDao jpaMovieDao;
    private final JdbcReviewService reviewService;
    private final JdbcGenreService genreService;

    @Override
    public List<MovieResponse> getAll() {
        return movieDao.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public List<Movie> findByTitle(String title) {
        return singletonList(
                movieDao.findBy(title)
                        .orElseThrow(() -> new UserNotFoundException(
                                "User not found")));
    }

    @Override
    public MovieRequest save(MovieRequest dto) {
        dto.setMovieId(UUID.randomUUID().getMostSignificantBits());
        Movie movie = movieDao.save(mapper.toEntity(dto));
        List<Genre> genres = genreService.saveAll(dto.getGenres());
        Review review = reviewService.save(dto.getReview());
        return mapper.toDto(movie, genres, review);
    }

    @Override
    public List<MovieDetails> saveMovies(List<MovieDetails> movies) {
        List<Integer> movieIds =
                movies.stream()
                      .map(MovieDetails::getId)
                      .toList();
        return jpaMovieDao.saveAll(
                movies.stream().filter(movie -> !jpaMovieDao
                              .findAllById(movieIds).contains(movie))
                      .toList());
    }

    public List<MovieDetailsResponse> getAllMovieDetails() {
        return mapper.toListDto(
                jpaMovieDao.getAllMovieDetailsWithGenres());
    }

    @Override
    public ResponseEntity<List<MovieDetailsResponse>> fetchByName(
            String movieName) {
        String searchURL = String.format(
                "%s?api_key=%s&language=%s&query=%s",
                baseApi, apiKey, "en-US", movieName);
        return ResponseEntity.ok(Objects.requireNonNull(
                new RestTemplate().getForEntity(searchURL, MovieSearchResponse.class)
                                  .getBody()).getResults());
    }
}
