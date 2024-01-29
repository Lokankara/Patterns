package patterns.rest.controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import patterns.rest.model.entity.Movie;
import patterns.rest.service.jdbc.JdbcMovieService;

import java.io.IOException;
import java.util.List;

@WebServlet("/api")
@RequiredArgsConstructor
public class ApiMovieCatalogServlet
        extends HttpServlet {

    private final JdbcMovieService movieService;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");

//        jdbcMovieService.getApiFromUrl(title);
        List<Movie> movies = movieService.getAll();
        request.setAttribute("movies", movies);
        request.setAttribute("title", title);
        request.getRequestDispatcher("/WEB-INF/views/api.jsp")
                .forward(request, response);
    }
}
