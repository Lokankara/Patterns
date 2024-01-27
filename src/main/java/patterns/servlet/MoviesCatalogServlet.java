package patterns.servlet;

import lombok.RequiredArgsConstructor;
import patterns.model.entity.Movie;
import patterns.service.JdbcMovieService;
import patterns.service.MovieService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/movie")
@RequiredArgsConstructor
public class MoviesCatalogServlet
        extends HttpServlet {
    private final MovieService jdbcMovieService = new JdbcMovieService();

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
