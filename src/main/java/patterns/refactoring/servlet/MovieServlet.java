package patterns.refactoring.servlet;

import lombok.RequiredArgsConstructor;
import patterns.refactoring.factory.Type;
import patterns.refactoring.model.dto.MovieDto;
import patterns.refactoring.model.entity.Actor;
import patterns.refactoring.model.entity.Genre;
import patterns.refactoring.model.entity.Movie;
import patterns.refactoring.model.entity.Review;
import patterns.refactoring.service.JdbcMovieService;
import patterns.refactoring.service.MovieService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;

@WebServlet("/movie/*")
@RequiredArgsConstructor
public class MovieServlet
        extends HttpServlet {

    private final MovieService movieService = new JdbcMovieService();

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {
        String title = extractFromUrl(req.getRequestURL().toString());
        List<Movie> movies = movieService.findByTitle(title);
        req.setAttribute("movies", movies);
        req.getRequestDispatcher("/WEB-INF/views/index.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {
        MovieDto lordRings = extracted();
        MovieDto save = movieService.save(lordRings);
        req.setAttribute("movie", save);
    }

    private static MovieDto extracted() {
        List<String> listGenre = asList("Comedy",
                "Fantasy",
                "Action",
                "Drama",
                "Romance",
                "Thriller",
                "Adventure",
                "Sci-Fi",
                "Mystery");

        List<Genre> genres = listGenre.stream()
                .map(genre -> Genre.builder().name(genre).build())
                .toList();

        Review lordRingReview = Review.builder()
                .country("NZ")
                .overview("An epic fantasy adventure")
                .director("Peter Jackson")
                .actors(asList(Actor.builder().name("Elijah Wood").build(),
                        Actor.builder().name("Ian McKellen").build()))
                .rating(10)
                .build();

        return MovieDto.builder()
                .genres(asList(genres.get(0), genres.get(1), genres.get(2)))
                .title("Lord of the Rings")
                .priceCode(Type.NEW_RELEASE)
                .review(lordRingReview)
                .build();
    }

    private String extractFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }
}
