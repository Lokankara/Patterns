package patterns.refactoring.dao.mapper.result;

import patterns.refactoring.exception.DataException;
import patterns.refactoring.factory.Type;
import patterns.refactoring.model.entity.Actor;
import patterns.refactoring.model.entity.Genre;
import patterns.refactoring.model.entity.Movie;
import patterns.refactoring.model.entity.Review;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class MovieResultSetMapper
        extends ResultSetMapper<Movie> {
    private final GenreResultSetMapper genreMapper = new GenreResultSetMapper();
    private final ReviewResultSetMapper reviewMapper = new ReviewResultSetMapper();

    @Override
    public Movie mapRow(ResultSet resultSet) {
        try {
            Long id = resultSet.getLong("movie_id");
            String title = resultSet.getString("title");
            Type priceCode = Type.valueOf(resultSet.getString("price_code"));
            String[] genreNames = resultSet.getString("genre_name").split(",");
            List<Genre> genres = Arrays.stream(genreNames)
                    .map(genreName -> genreMapper.mapRow(resultSet))
                    .filter(Objects::nonNull)
                    .toList();
            Review review = reviewMapper.mapRow(resultSet);
            return Movie.builder()
                    .movieId(id)
                    .title(title)
                    .review(review)
                    .priceCode(priceCode)
                    .genres(genres)
                    .build();
        } catch (SQLException e) {
            throw new DataException(e.getMessage());
        }
    }

    @Override
    public Movie mapper(HttpServletRequest request) {
        String title = request.getParameter("title");
        Long id = Long.valueOf(request.getParameter("movie_id"));
        Type priceCode = Type.valueOf(request.getParameter("price_code"));
        String[] genreNames = request.getParameterValues("genre_name");
        List<Genre> genres = new ArrayList<>();
        if (genreNames != null) {
            genres = Arrays.stream(genreNames)
                    .map(genreName -> genreMapper.mapper(request))
                    .filter(Objects::nonNull)
                    .toList();
        }
        Review review = reviewMapper.mapper(request);
        return new Movie(id, title, review, priceCode, genres);
    }

    public Optional<Movie> join(List<Movie> movies) {
        if (movies.isEmpty()) {
            return Optional.empty();
        }

        List<Actor> allActors = new ArrayList<>();
        List<Genre> allGenres = new ArrayList<>();

        Movie firstMovie = movies.get(0);
        Review review = firstMovie.getReview();
        for (int i = 1; i < movies.size(); i++) {
            Movie currentMovie = movies.get(i);
            if (currentMovie.getMovieId().equals(firstMovie.getMovieId())) {
                allActors.addAll(currentMovie.getReview().getActors());
                allGenres.addAll(currentMovie.getGenres());
            }
        }
        review.setActors(new ArrayList<>(new HashSet<>(allActors)));
        Movie build = Movie.builder()
                .movieId(firstMovie.getMovieId())
                .review(review)
                .genres(new ArrayList<>(new HashSet<>(allGenres)))
                .priceCode(firstMovie.getPriceCode())
                .title(firstMovie.getTitle())
                .build();
        return Optional.of(build);
    }
}
