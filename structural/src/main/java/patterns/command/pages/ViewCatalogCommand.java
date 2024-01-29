package patterns.command.pages;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import patterns.rest.model.dto.MovieResponse;

import java.io.IOException;

@RequiredArgsConstructor
public class ViewCatalogCommand implements Command {
    private final MovieResponse movieResponse;

    @Override
    public String execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {
        movieResponse.viewMovies();
        return "view";
    }
}
