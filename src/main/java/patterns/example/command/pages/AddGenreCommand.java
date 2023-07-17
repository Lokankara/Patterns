package patterns.example.command.pages;

import lombok.RequiredArgsConstructor;
import patterns.example.model.entity.Genre;
import patterns.example.model.entity.Movie;
import patterns.example.model.dto.MovieCatalog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class AddGenreCommand
        implements Command {

    private final Genre genre;
    private final Movie movie;
    private final MovieCatalog movieCatalog;

    @Override
    public String execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {
        movie.addGenre(genre);
        movieCatalog.addMovie(movie);
        return "add-genre";
    }
}
