package patterns.rest.service.mapper;

import org.springframework.stereotype.Component;
import patterns.rest.model.dto.MovieRequest;
import patterns.rest.model.entity.Genre;
import patterns.rest.model.entity.Movie;
import patterns.rest.model.entity.Review;

import java.util.List;

@Component
public class MovieMapper {
    public Movie toEntity(MovieRequest dto) {
        return Movie.builder()
                .priceCode(dto.getPriceCode())
                .movieId(dto.getMovieId())
                .review(dto.getReview())
                .genres(dto.getGenres())
                .title(dto.getTitle())
                .build();
    }

    public MovieRequest toDto(
            Movie movie,
            List<Genre> genres,
            Review review) {
        return MovieRequest.builder()
                           .priceCode(movie.getPriceCode())
                           .movieId(movie.getMovieId())
                           .title(movie.getTitle())
                           .review(review)
                           .genres(genres)
                           .build();
    }
}
