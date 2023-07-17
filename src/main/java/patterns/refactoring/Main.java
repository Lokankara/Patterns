package patterns.refactoring;

import patterns.refactoring.factory.MovieType;
import patterns.refactoring.factory.MovieTypeFactory;
import patterns.refactoring.factory.Type;
import patterns.refactoring.model.dto.MovieCatalog;
import patterns.refactoring.model.entity.Customer;
import patterns.refactoring.model.entity.Genre;
import patterns.refactoring.model.entity.Movie;
import patterns.refactoring.model.entity.Rental;
import patterns.refactoring.model.entity.Review;

import java.util.List;
import java.util.logging.Logger;

import static java.util.Arrays.asList;

public class Main {

    private static final Logger log = Logger.getLogger(Main.class.getName());
    private static final MovieCatalog movieCatalog = new MovieCatalog();

    public static void main(String[] args) {
        MovieTypeFactory factory = MovieTypeFactory.getInstance();
        MovieType regular = factory.create("Regular");
        MovieType child = factory.create("Child");
        MovieType new_release = factory.create("New Release");
        //TODO validators
        initData();
        List<Movie> movies = movieCatalog.getMovies();
        List<Rental> rentals = List.of(new Rental(1L, movies.get(0), 1),
                new Rental(2L, movies.get(1), 4),
                new Rental(3L, movies.get(2), 5));
        Customer customer = new Customer(1L, "Jack Sparrow", rentals);
        String statement = customer.statement();
        log.info(statement);
    }
    public static void initData() {
        List<String> listGenre = asList("Comedy",
                "Fantasy",
                "Action",
                "Drama",
                "Romance",
                "Thriller",
                "Adventure",
                "Sci-Fi",
                "History",
                "Western",
                "Music",
                "Horror",
                "Animation",
                "Mystery");

        List<Genre> genres = listGenre.stream()
                .map(genre -> Genre.builder().name(genre).build())
                .toList();

        Review ramboReview = Review.builder()
                .country("USA")
                .overview("An action-packed thriller")
                .director("Sylvester Stallone")
//                .actors(asList("Sylvester Stallone", "Richard Crenna"))
                .build();

        Review lordRingReview = Review.builder()
                .country("NZ")
                .overview("An epic fantasy adventure")
                .director("Peter Jackson")
//                .actors(asList("Elijah Wood", "Ian McKellen"))
                .rating(10)
                .build();

        Review potterReview = Review.builder()
                .country("UK")
                .overview("A magical journey of friendship and bravery")
                .director("Chris Columbus")
//                .actors(asList("Daniel Radcliffe", "Emma Watson"))
                .rating(9)
                .build();

        Movie rambo = Movie.builder()
                .title("Rambo")
                .genres(asList(genres.get(5), genres.get(6)))
                .priceCode(Type.REGULAR)
                .review(ramboReview)
                .build();

        Movie lordRings = Movie.builder()
                .genres(asList(genres.get(1), genres.get(2), genres.get(9)))
                .title("Lord of the Rings")
                .priceCode(Type.NEW_RELEASE)
                .review(lordRingReview)
                .build();

        Movie harryPotter = Movie.builder()
                .genres(asList(genres.get(0), genres.get(1), genres.get(2)))
                .title("Harry Potter")
                .priceCode(Type.CHILDREN)
                .review(potterReview)
                .build();
        movieCatalog.addMovie(rambo);
        movieCatalog.addMovie(lordRings);
        movieCatalog.addMovie(harryPotter);
    }
}