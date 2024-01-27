package patterns.factory.leisure.movie;

import lombok.AllArgsConstructor;
import patterns.factory.leisure.creator.FantasyEntityFactory;
import patterns.factory.leisure.creator.Genre;
import patterns.factory.leisure.creator.Language;
import patterns.factory.leisure.creator.ScienceEntityFactory;

@AllArgsConstructor
public class MovieEntityFactory {

    private final Language language;

    public Movie createMovie(Genre genre) {
        switch (genre) {
            case FANTASY -> {
                return new FantasyEntityFactory(language).createMovie();
            }
            case SCIENCE -> {
                return new ScienceEntityFactory(language).createMovie();
            }
            default ->
                    throw new IllegalStateException("Unexpected value: " + genre);
        }
    }
}
