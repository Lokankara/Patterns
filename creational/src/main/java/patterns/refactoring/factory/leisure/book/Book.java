package patterns.refactoring.factory.leisure.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import patterns.refactoring.factory.leisure.creator.Genre;
import patterns.refactoring.factory.leisure.creator.Language;

@Getter
@AllArgsConstructor
public abstract class Book {

    protected Genre genre;
    protected Language language;

    abstract void read();
}
