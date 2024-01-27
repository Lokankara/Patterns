package patterns.factory.leisure.book;

import lombok.extern.slf4j.Slf4j;
import patterns.factory.leisure.creator.Genre;
import patterns.factory.leisure.creator.Language;

@Slf4j
public class FantasyBook
        extends Book {

    public FantasyBook(Language language) {
        super(Genre.FANTASY, language);
        read();
    }

    @Override
    protected void read() {
        log.info("{} {}",
                 this.getClass().getSimpleName(),
                 language.name());
    }
}
