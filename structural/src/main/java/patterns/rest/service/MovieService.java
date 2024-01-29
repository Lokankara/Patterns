package patterns.rest.service;

import patterns.rest.model.dto.MovieRequest;
import patterns.rest.model.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAll();

    List<Movie> findByTitle(String title);

    MovieRequest save(MovieRequest movie);
}
