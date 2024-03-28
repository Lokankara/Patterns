package patterns.factory.leisure.creator;

import lombok.AllArgsConstructor;
import patterns.factory.leisure.book.Book;
import patterns.factory.leisure.book.ScienceBook;
import patterns.factory.leisure.movie.ScienceMovie;
import patterns.factory.leisure.movie.Movie;

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
