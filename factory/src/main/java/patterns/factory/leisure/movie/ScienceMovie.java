package patterns.factory.leisure.movie;

import lombok.extern.slf4j.Slf4j;
import patterns.factory.leisure.creator.Genre;
import patterns.factory.leisure.creator.Language;

@Slf4j
public class ScienceMovie
        extends Movie {

    public ScienceMovie(final Language language) {
        super(Genre.SCIENCE, language);
        watch();
    }

    @Override
    public void watch() {
        log.info("{} {}", this.getClass().getSimpleName(), language);
    }
}
