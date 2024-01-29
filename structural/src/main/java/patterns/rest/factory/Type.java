package patterns.rest.factory;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Type {
    REGULAR(new RegularMovieType()),
    NEW_RELEASE(new NewReleaseType()),
    CHILDREN(new ChildMovieType());

    private final MovieType movieType;
}
