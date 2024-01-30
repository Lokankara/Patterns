package patterns.rest.service;

import org.springframework.http.ResponseEntity;
import patterns.rest.model.dto.MovieDetailsResponse;
import patterns.rest.model.dto.MovieRequest;
import patterns.rest.model.dto.MovieResponse;
import patterns.rest.model.entity.Movie;
import patterns.rest.model.entity.MovieDetails;

import java.util.List;

public interface MovieService {
    List<MovieResponse> getAll();

    List<Movie> findByTitle(String title);

    MovieRequest save(MovieRequest movie);

    List<MovieDetails> saveMovies(List<MovieDetails> movies);

    List<MovieDetailsResponse> getAllMovieDetails();

    ResponseEntity<List<MovieDetailsResponse>> fetchByName(String movieName);
}
