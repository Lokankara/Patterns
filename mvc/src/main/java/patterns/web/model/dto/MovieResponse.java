package patterns.web.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import patterns.web.model.entity.Movie;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponse {

    private String title;
    private String country;
    private String overview;
    private String director;
    private String backdropPath;
    private String genres;
    private String actors;

    private Double rating;

    private List<Movie> movieList;
    public MovieResponse(MovieRequest movieRequest) {
        this.title = movieRequest.getTitle();
        this.country = movieRequest.getReview().getCountry();
        this.overview = movieRequest.getReview().getOverview();
        this.director = movieRequest.getReview().getDirector();
        this.backdropPath = movieRequest.getReview().getBackdropPath();
        this.rating = movieRequest.getReview().getRating();
        this.genres = movieRequest.getGenres().toString();
        this.actors = movieRequest.getActors().toString();
    }

    public void viewMovies() {
        movieList.forEach(movie -> log.info("{}Genre: {}",
                                            movie.getTitle(),
                                            movie.getGenres()));
    }

    public void addMovie(@NonNull Movie movie) {
        if (movieList == null) {
            movieList = new ArrayList<>();
        }
        movieList.add(movie);
    }
}
