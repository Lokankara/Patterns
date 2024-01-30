package patterns.rest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import patterns.rest.model.dto.MovieDetailsResponse;
import patterns.rest.model.dto.MovieResponse;
import patterns.rest.model.entity.MovieDetails;
import patterns.rest.service.MovieService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MoviesCatalogController {

    private final MovieService movieService;
    private static final String MOVIES = "movies";

    @GetMapping
    public String getAllMovies(Model model) {
        List<MovieResponse> movies = movieService.getAll();
        log.info(movies.toString());
        model.addAttribute(MOVIES, movies);
        return "index";
    }

    @PostMapping("/save")
    public String saveMovies(
            @RequestBody List<MovieDetails> movies, Model model) {
        List<MovieDetails> movieDetails = movieService.saveMovies(movies);
        model.addAttribute(MOVIES, movieDetails);
        return "redirect:api/movies/details";
    }

    @GetMapping("/search")
    public String getAllMoviesDetails(Model model) {
        List<MovieDetailsResponse> movies = movieService.getAllMovieDetails();
        log.info(movies.toString());
        model.addAttribute(MOVIES, movies);
        return "search";
    }

    @PostMapping("/search")
    public ResponseEntity<List<MovieDetailsResponse>> searchMovie(
            @RequestParam String movieName) {
        return movieService.fetchByName(movieName);
    }
}
