package patterns.command.pages;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import patterns.rest.model.dto.MovieResponse;
import patterns.rest.model.entity.Genre;
import patterns.rest.model.entity.Movie;

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
