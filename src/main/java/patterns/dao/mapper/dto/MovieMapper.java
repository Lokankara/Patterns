package patterns.dao.mapper.dto;

import patterns.model.dto.MovieDto;
import patterns.model.entity.Genre;
import patterns.model.entity.Movie;
import patterns.model.entity.Review;

import java.util.List;

public class MovieMapper {
    public Movie toEntity(MovieDto dto) {
        return Movie.builder()
                .priceCode(dto.getPriceCode())
                .movieId(dto.getMovieId())
                .review(dto.getReview())
                .genres(dto.getGenres())
                .title(dto.getTitle())
                .build();
    }

    public MovieDto toDto(
            Movie movie,
            List<Genre> genres,
            Review review) {
        return MovieDto.builder()
                .priceCode(movie.getPriceCode())
                .movieId(movie.getMovieId())
                .title(movie.getTitle())
                .review(review)
                .genres(genres)
                .build();
    }
}
