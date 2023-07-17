package patterns.example.command.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FilterEnum {
    LOGIN_PAGE(new LoginFilterCommand()),
    LOGIN(new LoginFilterCommand()),
    ERROR_PAGE(new ErrorFilterCommand());

    private final FilterCommand filter;
}
