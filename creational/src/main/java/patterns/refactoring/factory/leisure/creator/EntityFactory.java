package patterns.refactoring.factory.leisure.creator;

import patterns.refactoring.factory.leisure.book.Book;
import patterns.refactoring.factory.leisure.movie.Movie;

public interface EntityFactory {

    Movie createMovie();

    Book createBook();
}
