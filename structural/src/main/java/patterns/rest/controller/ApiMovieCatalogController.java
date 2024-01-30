package patterns.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import patterns.rest.model.dto.MovieDetailsResponse;
import patterns.rest.model.dto.MovieResponse;
import patterns.rest.service.jdbc.JdbcMovieService;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class ApiMovieCatalogController {

    private final JdbcMovieService movieService;

    @GetMapping
    public List<MovieResponse> getMovieBy(
            @RequestParam(name = "title", required = false) String title) {
        return movieService.getAll();
    }

    @GetMapping("/details")
    public List<MovieDetailsResponse> getAllMoviesDetails() {
        return movieService.getAllMovieDetails();
    }
}
