package patterns.factory.leisure.movie;

import lombok.extern.slf4j.Slf4j;
import patterns.factory.leisure.creator.Genre;
import patterns.factory.leisure.creator.Language;

@Slf4j
public class FantasyMovie
        extends Movie {

    public FantasyMovie(final Language language) {
        super(Genre.FANTASY, language);
        watch();
    }

    @Override
    public void watch() {
        log.info("{} {}", this.getClass().getSimpleName(), language.name());
    }
}
