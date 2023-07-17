package patterns.refactoring.factory.leisure.movie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import patterns.refactoring.factory.leisure.creator.Genre;
import patterns.refactoring.factory.leisure.creator.Language;

@Getter
@AllArgsConstructor
public abstract class Movie {

    protected Genre genre;
    protected Language language;

    abstract void watch();
}