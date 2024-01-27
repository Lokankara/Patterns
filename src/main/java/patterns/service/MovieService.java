package patterns.service;

import patterns.model.dto.MovieDto;
import patterns.model.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAll();

    List<Movie> findByTitle(String title);

    MovieDto save(MovieDto movie);
}
