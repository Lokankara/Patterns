package patterns.refactoring.factory.leisure.movie;

import org.junit.jupiter.api.Test;
import patterns.refactoring.factory.leisure.creator.Genre;
import patterns.refactoring.factory.leisure.creator.Language;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MovieEntityFactoryTest {

    @Test
    void createFantasyMovie() {
        MovieEntityFactory factory = new MovieEntityFactory(Language.ENGLISH);
        Movie fantasyMovie = factory.createMovie(Genre.FANTASY);
        assertNotNull(fantasyMovie);
        assertTrue(fantasyMovie instanceof FantasyMovie);
        assertEquals(Language.ENGLISH, (fantasyMovie).getLanguage());
        assertEquals(Genre.FANTASY, fantasyMovie.getGenre());
    }

    @Test
    void createScienceMovie() {
        MovieEntityFactory factory = new MovieEntityFactory(Language.GERMAN);
        Movie scienceMovie = factory.createMovie(Genre.SCIENCE);

        assertNotNull(scienceMovie);
        assertTrue(scienceMovie instanceof ScienceMovie);
        assertEquals(Language.GERMAN, (scienceMovie).getLanguage());
        assertEquals(Genre.SCIENCE, scienceMovie.getGenre());
    }


    @Test
    void createUnsupportedGenreMovie() {
        MovieEntityFactory factory = new MovieEntityFactory(Language.FRENCH);
        assertThrows(IllegalStateException.class, () -> factory.createMovie(Genre.HUMOR));
    }
}
