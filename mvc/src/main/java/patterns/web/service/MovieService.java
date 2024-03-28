package patterns.web.service;

import org.springframework.http.ResponseEntity;
import patterns.web.model.dto.MovieDetailsResponse;
import patterns.web.model.dto.MovieRequest;
import patterns.web.model.dto.MovieResponse;
import patterns.web.model.entity.Movie;
import patterns.web.model.entity.MovieDetails;

import java.util.List;

public interface MovieService {
    List<MovieResponse> getAll();

    List<Movie> findByTitle(String title);

    MovieRequest save(MovieRequest movie);

    List<MovieDetails> saveMovies(List<MovieDetails> movies);

    List<MovieDetailsResponse> getAllMovieDetails();

    ResponseEntity<List<MovieDetailsResponse>> fetchByName(String movieName);
}
