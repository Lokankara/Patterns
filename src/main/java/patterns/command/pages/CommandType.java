package patterns.command.pages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommandType {
    ERROR(new ErrorCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    SEARCH(new FindCommand()),
    ADD_MOVIE(new AddMovieCommand()),
    MOVIES(new GetAllMoviesCommand());

    private final Command command;
}
