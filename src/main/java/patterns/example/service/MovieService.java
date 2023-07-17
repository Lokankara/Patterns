package patterns.example.service;

import patterns.example.model.dto.MovieDto;
import patterns.example.model.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAll();

    List<Movie> findByTitle(String title);

    MovieDto save(MovieDto movie);
}
