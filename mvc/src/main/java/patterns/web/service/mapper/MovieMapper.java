package patterns.web.service.mapper;

import org.springframework.stereotype.Component;
import patterns.web.model.dto.MovieDetailsResponse;
import patterns.web.model.dto.MovieRequest;
import patterns.web.model.dto.MovieResponse;
import patterns.web.model.entity.Genre;
import patterns.web.model.entity.Movie;
import patterns.web.model.entity.MovieDetails;
import patterns.web.model.entity.Review;

import java.util.List;

@Component
public class MovieMapper {
    public Movie toEntity(MovieRequest dto) {
        return Movie
                .builder()
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
        return MovieRequest
                .builder()
                .priceCode(movie.getPriceCode())
                .movieId(movie.getMovieId())
                .title(movie.getTitle())
                .review(review)
                .genres(genres)
                .build();
    }

    public MovieResponse toDto(Movie movie) {
        return new MovieResponse(MovieRequest
                                         .builder()
                                         .priceCode(movie.getPriceCode())
                                         .movieId(movie.getMovieId())
                                         .title(movie.getTitle())
                                         .review(movie.getReview())
                                         .genres(movie.getGenres())
                                         .actors(movie.getReview().getActors())
                                         .build());
    }

    public List<MovieDetailsResponse> toListDto(List<MovieDetails> all) {
        return all.stream().map(this::toDto).toList();
    }

    public MovieDetailsResponse toDto(MovieDetails details) {
        MovieDetailsResponse dto = new MovieDetailsResponse();
        dto.setId(details.getId());
        dto.setAdult(details.isAdult());
        dto.setBackdropPath(details.getBackdropPath());
        dto.setGenreIds(details.getGenreIds());
        dto.setOriginalLanguage(details.getOriginalLanguage());
        dto.setOriginalTitle(details.getOriginalTitle());
        dto.setOverview(details.getOverview());
        dto.setPopularity(details.getPopularity());
        dto.setPosterPath(details.getPosterPath());
        dto.setReleaseDate(details.getReleaseDate());
        dto.setTitle(details.getTitle());
        dto.setVideo(details.isVideo());
        dto.setVoteAverage(details.getVoteAverage());
        dto.setVoteCount(details.getVoteCount());
        return dto;
    }
}
