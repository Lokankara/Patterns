package patterns.service;

import lombok.SneakyThrows;
import patterns.dao.MovieDao;
import patterns.dao.jdbc.JdbcMovieDao;
import patterns.dao.mapper.dto.MovieMapper;
import patterns.exception.UserNotFoundException;
import patterns.model.dto.MovieDto;
import patterns.model.entity.Genre;
import patterns.model.entity.Movie;
import patterns.model.entity.Review;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import static java.util.Collections.singletonList;

public class JdbcMovieService
        implements MovieService {
    private final MovieDao movieDao;
    private final MovieMapper mapper;
    private final JdbcReviewService reviewService;
    private final JdbcGenreService genreService;
    private static final Logger log = Logger.getLogger(JdbcMovieService.class.getName());

    public JdbcMovieService() {
        this.mapper = new MovieMapper();
        this.movieDao = new JdbcMovieDao();
        this.genreService = new JdbcGenreService();
        this.reviewService = new JdbcReviewService();
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.findAll();
    }

    @Override
    public List<Movie> findByTitle(String title) {
        return singletonList(movieDao.findBy(title)
                                     .orElseThrow(() -> new UserNotFoundException(
                                             "User not found")));
    }

    @Override
    public MovieDto save(MovieDto dto) {
        dto.setMovieId(UUID.randomUUID().getMostSignificantBits());
        Movie movie = movieDao.save(mapper.toEntity(dto));
        List<Genre> genres = genreService.saveAll(dto.getGenres());
        Review review = reviewService.save(dto.getReview());
        return mapper.toDto(movie, genres, review);
    }

    public List<Movie> getAllFromApi() {
        return movieDao.findAll();
    }

    public void importMoviesFromFile(String filePath) {
    }

    @SneakyThrows
    public void getApiFromUrl(String title) {
        String API_KEY = "249f222afb1002186f4d88b2b5418b55";
        String API_SEARCH = "https://api.themoviedb.org/3/search/movie?api_key=" + API_KEY + "&language=en-US&query=" + title;

        HttpURLConnection connection = (HttpURLConnection) new URL(API_SEARCH).openConnection();
        connection.setRequestMethod("GET");
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String inputLine;
            StringBuilder responses = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                log.info(responses.append(inputLine).toString());
            }
            in.close();
        }
    }
}
