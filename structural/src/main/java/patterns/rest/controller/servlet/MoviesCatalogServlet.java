package patterns.rest.controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import patterns.rest.model.entity.Movie;
import patterns.rest.service.MovieService;

import java.io.IOException;
import java.util.List;

@WebServlet("/movie")
@RequiredArgsConstructor
public class MoviesCatalogServlet
        extends HttpServlet {

    private final MovieService jdbcMovieService;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        List<Movie> movies = jdbcMovieService.getAll();
        request.setAttribute("movies", movies);
        request.getRequestDispatcher("/WEB-INF/views/index.jsp")
                .forward(request, response);
    }
}
