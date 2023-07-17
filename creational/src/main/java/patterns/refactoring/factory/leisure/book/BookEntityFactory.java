package patterns.refactoring.factory.leisure.book;

import lombok.AllArgsConstructor;
import patterns.refactoring.factory.leisure.creator.FantasyEntityFactory;
import patterns.refactoring.factory.leisure.creator.Genre;
import patterns.refactoring.factory.leisure.creator.Language;
import patterns.refactoring.factory.leisure.creator.ScienceEntityFactory;

@AllArgsConstructor
public class BookEntityFactory {

    private final Language language;

    public Book createBook(Genre genre) {
        switch (genre) {
            case FANTASY -> {
                return new FantasyEntityFactory(language).createBook();
            }
            case SCIENCE -> {
                return new ScienceEntityFactory(language).createBook();
            }
            default ->
                    throw new IllegalStateException("Unexpected value: " + genre);
        }
    }
}
