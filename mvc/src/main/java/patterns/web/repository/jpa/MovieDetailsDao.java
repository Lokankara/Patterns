package patterns.web.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import patterns.web.model.entity.MovieDetails;

import java.util.List;

@Repository
public interface MovieDetailsDao
        extends JpaRepository<MovieDetails, Integer> {

    @Query("SELECT md FROM MovieDetails md LEFT JOIN FETCH md.genreIds")
    List<MovieDetails> getAllMovieDetailsWithGenres();
}
