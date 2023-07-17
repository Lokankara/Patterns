package patterns.refactoring.dao.template;

public class Query {

    private Query(){
    }
    public static final String INSERT_ACTOR =
            "INSERT INTO actors (actor_id, name) VALUES (?, ?)";
    public static final String INSERT_GENRE  = "INSERT INTO genres (genre_id, name) VALUES (?, ?)";
    public static final String INSERT_REVIEW =
            "INSERT INTO reviews (review_id, country, overview, backdrop_path, rating) VALUES (?, ?, ?, ?, ?)";
    public static final String INSERT_MOVIE =
            "INSERT INTO movies (movie_id, title) VALUES (?, ?)";
    public static final String SELECT_ALL_MOVIE =
            "SELECT m.movie_id, m.title, m.price_code, "
                    + "r.review_id, r.country, r.overview, r.director, r.backdrop_path, r.rating, "
                    + "g.genre_id, g.name AS genre_name, a.actor_id, a.name "
                    + "AS actor_name FROM movies m "
                    + "JOIN movie_review mr ON m.movie_id = mr.movie_id "
                    + "JOIN reviews r ON mr.review_id = r.review_id "
                    + "JOIN movie_genres mg ON m.movie_id = mg.movie_id "
                    + "JOIN genres g ON mg.genre_id = g.genre_id "
                    + "JOIN review_actors ra ON r.review_id = ra.review_id "
                    + "JOIN actors a ON ra.actor_id = a.actor_id ";

    public static final String SELECT_BY_TITLE =
            "SELECT m.movie_id, m.title, m.price_code, "
           + "r.review_id, r.country, r.overview, r.director, "
           + "r.backdrop_path, r.rating, "
           + "g.genre_id, g.name AS genre_name, a.actor_id, a.name "
           + "AS actor_name FROM movies m "
           + "JOIN movie_review mr ON m.movie_id = mr.movie_id "
           + "JOIN reviews r ON mr.review_id = r.review_id "
           + "JOIN movie_genres mg ON m.movie_id = mg.movie_id "
           + "JOIN genres g ON mg.genre_id = g.genre_id "
           + "JOIN review_actors ra ON r.review_id = ra.review_id "
           + "JOIN actors a ON ra.actor_id = a.actor_id "
           + "WHERE m.title = ?";

    public static final String SELECT_UNIQUE = "SELECT m.title, r.overview, "
            + "r.director, r.backdrop_path, r.rating, "
            + "string_agg(DISTINCT a.name, ',') as actors, "
            + "string_agg(DISTINCT g.name, ',') as genres "
            + "FROM movies m " + "LEFT JOIN movie_review mr ON m.movie_id = mr.movie_id "
            + "LEFT JOIN reviews r ON mr.review_id = r.review_id "
            + "LEFT JOIN review_actors ra ON r.review_id = ra.review_id "
            + "LEFT JOIN actors a ON ra.actor_id = a.actor_id "
            + "LEFT JOIN movie_genres mg ON m.movie_id = mg.movie_id "
            + "LEFT JOIN genres g ON mg.genre_id = g.genre_id "
            + "WHERE m.title = ? "
            + "GROUP BY m.movie_id, r.overview, r.director, r.backdrop_path, "
            + "r.rating ";
}
