package patterns.example.servlet;

import lombok.RequiredArgsConstructor;
import patterns.example.model.entity.Movie;
import patterns.example.service.JdbcMovieService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api")
@RequiredArgsConstructor
public class ApiMovieCatalogServlet
        extends HttpServlet {

    private final JdbcMovieService movieService = new JdbcMovieService();

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
