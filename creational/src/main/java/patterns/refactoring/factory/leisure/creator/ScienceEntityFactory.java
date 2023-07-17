package patterns.refactoring.factory.leisure.creator;

import lombok.AllArgsConstructor;
import patterns.refactoring.factory.leisure.book.Book;
import patterns.refactoring.factory.leisure.book.ScienceBook;
import patterns.refactoring.factory.leisure.movie.Movie;
import patterns.refactoring.factory.leisure.movie.ScienceMovie;

@AllArgsConstructor
public class ScienceEntityFactory implements EntityFactory {

    protected Language language;

    @Override
    public Movie createMovie() {
        return new ScienceMovie(language);
    }

    @Override
    public Book createBook() {
        return new ScienceBook(language);
    }
}
