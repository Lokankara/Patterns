package patterns.rest.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import patterns.rest.factory.Type;
import patterns.rest.model.dto.MovieRequest;
import patterns.rest.model.entity.Actor;
import patterns.rest.model.entity.Genre;
import patterns.rest.model.entity.Movie;
import patterns.rest.model.entity.Review;
import patterns.rest.service.MovieService;

import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;

@WebServlet("/movie/*")
@RequiredArgsConstructor
public class MovieServlet
        extends HttpServlet {

    private final MovieService movieService;

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {
        String title = extractFromUrl(req.getRequestURL().toString());
        List<Movie> movies = movieService.findByTitle(title);
        req.setAttribute("movies", movies);
        req.getRequestDispatcher("/WEB-INF/templates/index.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp) {
        MovieRequest lordRings = extracted();
        MovieRequest save = movieService.save(lordRings);
        req.setAttribute("movie", save);
    }

    private static MovieRequest extracted() {
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

        return MovieRequest.builder()
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
