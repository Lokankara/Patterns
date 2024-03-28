package patterns.factory.leisure.creator;

import lombok.AllArgsConstructor;
import patterns.factory.leisure.book.FantasyBook;
import patterns.factory.leisure.movie.FantasyMovie;
import patterns.factory.leisure.book.Book;
import patterns.factory.leisure.movie.Movie;

@AllArgsConstructor
public class FantasyEntityFactory
        implements EntityFactory {

    private Language language;

    @Override
    public Movie createMovie() {
        return new FantasyMovie(language);
    }

    @Override
    public Book createBook() {
        return new FantasyBook(language);
    }
}
