package patterns.refactoring.factory.leisure.creator;

import lombok.AllArgsConstructor;
import patterns.refactoring.factory.leisure.book.Book;
import patterns.refactoring.factory.leisure.book.FantasyBook;
import patterns.refactoring.factory.leisure.movie.FantasyMovie;
import patterns.refactoring.factory.leisure.movie.Movie;

@AllArgsConstructor
public class FantasyEntityFactory
        implements EntityFactory {

    protected Language language;

    @Override
    public Movie createMovie() {
        return new FantasyMovie(language);
    }

    @Override
    public Book createBook() {
        return new FantasyBook(language);
    }
}
