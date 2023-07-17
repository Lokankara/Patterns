package patterns.refactoring.service;

import patterns.refactoring.model.dto.MovieDto;
import patterns.refactoring.model.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAll();

    List<Movie> findByTitle(String title);

    MovieDto save(MovieDto movie);
}
