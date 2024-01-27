package patterns.factory.leisure.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import patterns.factory.leisure.creator.Genre;
import patterns.factory.leisure.creator.Language;

@Getter
@AllArgsConstructor
public abstract class Book {

    protected Genre genre;
    protected Language language;

    abstract void read();
}
