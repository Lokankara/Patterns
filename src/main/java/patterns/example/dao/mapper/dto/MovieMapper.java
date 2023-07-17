package patterns.example.dao.mapper.dto;

import patterns.example.model.dto.MovieDto;
import patterns.example.model.entity.Genre;
import patterns.example.model.entity.Movie;
import patterns.example.model.entity.Review;

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
