package patterns.factory.leisure.creator;

import patterns.factory.leisure.book.Book;
import patterns.factory.leisure.movie.Movie;

public interface EntityFactory {

    Movie createMovie();

    Book createBook();
}
