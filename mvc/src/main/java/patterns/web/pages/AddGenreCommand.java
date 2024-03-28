package patterns.web.pages;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import patterns.web.model.dto.MovieResponse;
import patterns.web.model.entity.Genre;
import patterns.web.model.entity.Movie;

import java.io.IOException;

@RequiredArgsConstructor
public class AddGenreCommand
        implements Command {

    private final Genre genre;
    private final Movie movie;
    private final MovieResponse movieResponse;

    @Override
    public String execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {
        movie.addGenre(genre);
        movieResponse.addMovie(movie);
        return "add-genre";
    }
}
