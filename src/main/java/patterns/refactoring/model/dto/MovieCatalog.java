package patterns.refactoring.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import patterns.refactoring.model.entity.Movie;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieCatalog {
    private List<Movie> movies;

    public void viewMovies() {
        movies.forEach(movie -> System.out.printf("%s%nGenre: %s%n",
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
