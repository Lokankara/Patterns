package patterns.refactoring.command.pages;

import lombok.RequiredArgsConstructor;
import patterns.refactoring.model.dto.MovieCatalog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class ViewCatalogCommand implements Command {
    private final MovieCatalog movieCatalog;

    @Override
    public String execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {
        movieCatalog.viewMovies();
        return "view";
    }
}
