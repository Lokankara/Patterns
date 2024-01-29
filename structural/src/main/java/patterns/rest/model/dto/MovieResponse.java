package patterns.rest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import patterns.rest.model.entity.Movie;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponse {
    private List<Movie> movies;

    public void viewMovies() {
        movies.forEach(movie -> log.info("{}Genre: {}",
                movie.getTitle(),
                movie.getGenres()));
    }

    public void addMovie(@NonNull Movie movie) {
        if (movies == null) {
            movies = new ArrayList<>();
        }
        movies.add(movie);
    }
}
