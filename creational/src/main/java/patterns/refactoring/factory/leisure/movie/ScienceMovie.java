package patterns.refactoring.factory.leisure.movie;

import lombok.extern.slf4j.Slf4j;
import patterns.refactoring.factory.leisure.creator.Genre;
import patterns.refactoring.factory.leisure.creator.Language;

@Slf4j
public class ScienceMovie
        extends Movie {

    public ScienceMovie(Language language) {
        super(Genre.SCIENCE, language);
        watch();
    }

    @Override
    public void watch() {
        log.info("{} {}", this.getClass().getSimpleName(), language);
    }
}
