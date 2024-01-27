package patterns.factory.leisure.movie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import patterns.factory.leisure.creator.Genre;
import patterns.factory.leisure.creator.Language;

@Getter
@AllArgsConstructor
public abstract class Movie {

    protected Genre genre;
    protected Language language;

    abstract void watch();
}
