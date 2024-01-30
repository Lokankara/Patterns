package patterns.rest.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class Genre {

    private Long genreId;
    private String name;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Movie> movies;

    public void addMovie(Movie movie) {
        movies.add(movie);
        movie.addGenre(this);
    }

    public void removeMovie(Movie movie) {
        movies.remove(movie);
        movie.removeGenre(this);
    }

    @Override
    public String toString() {
        return name;
    }
}
