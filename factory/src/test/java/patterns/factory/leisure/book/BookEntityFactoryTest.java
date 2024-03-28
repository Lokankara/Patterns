package patterns.factory.leisure.book;

import org.junit.jupiter.api.Test;
import patterns.factory.leisure.creator.Genre;
import patterns.factory.leisure.creator.Language;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookEntityFactoryTest {

    @Test
    void createFantasyBook() {
        BookEntityFactory factory = new BookEntityFactory(Language.ENGLISH);
        Book fantasyBook = factory.createBook(Genre.FANTASY);

        assertNotNull(fantasyBook);
        assertTrue(fantasyBook instanceof FantasyBook);
        assertEquals(Language.ENGLISH, (fantasyBook).getLanguage());
        assertEquals(Genre.FANTASY, fantasyBook.getGenre());
    }

    @Test
    void createScienceBook() {
        BookEntityFactory factory = new BookEntityFactory(Language.GERMAN);
        Book scienceBook = factory.createBook(Genre.SCIENCE);

        assertNotNull(scienceBook);
        assertTrue(scienceBook instanceof ScienceBook);
        assertEquals(Language.GERMAN, (scienceBook).getLanguage());
        assertEquals(Genre.SCIENCE, scienceBook.getGenre());
    }

    @Test
    void createUnsupportedGenreBook() {
        BookEntityFactory factory = new BookEntityFactory(Language.FRENCH);
        assertThrows(IllegalStateException.class, () -> factory.createBook(Genre.HUMOR));
    }
}
