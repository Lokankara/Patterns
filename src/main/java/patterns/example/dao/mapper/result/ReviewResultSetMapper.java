package patterns.example.dao.mapper.result;

import patterns.example.exception.DataException;
import patterns.example.model.entity.Actor;
import patterns.example.model.entity.Review;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ReviewResultSetMapper
        extends ResultSetMapper<Review> {
    private static final Logger log = Logger.getLogger(ReviewResultSetMapper.class.getName());
    private static final String[] reviewFields = {"country", "overview",
            "director", "backdrop_path", "rating", "actor_name", "review_id"};

    private final ActorResultSetMapper actorMapper = new ActorResultSetMapper();
    @Override
    public Review mapRow(ResultSet resultSet)  {
        try {
            Long id = resultSet.getLong(reviewFields[6]);
            String country = resultSet.getString(reviewFields[0]);
            String overview = resultSet.getString(reviewFields[1]);
            String director = resultSet.getString(reviewFields[2]);
            String backdropPath = resultSet.getString(reviewFields[3]);
            double rating = resultSet.getDouble(reviewFields[4]);
            String[] actorNames = resultSet.getString(reviewFields[5]).split(",");
            List<Actor> actors = Arrays.stream(actorNames)
                                .map(genreName -> actorMapper.mapRow(resultSet))
                                .filter(Objects::nonNull)
                                .toList();
            return new Review(id, country, overview,
                              director, backdropPath,
                              rating, actors);
        } catch (SQLException e){
            String message = "An error occurred while accessing the data: " + e.getMessage();
            log.warning(message);
            throw new DataException(message);
        }
    }

    @Override
    public Review mapper(HttpServletRequest request) {
        Map<String, String> params = Arrays.stream(reviewFields)
                .filter(key -> !"".equals(request.getParameter(key))
                        && request.getParameter(key) != null)
                .collect(Collectors.toMap(Function.identity(),
                                          request::getParameter));
        Long id = Long.valueOf(params.get(reviewFields[6]));
        String country = params.get(reviewFields[0]);
        String overview = params.get(reviewFields[1]);
        String director = params.get(reviewFields[2]);
        String backdropPath = params.get(reviewFields[3]);
        double rating = Double.parseDouble(
                params.getOrDefault(reviewFields[4], "0"));
        List<String> actors = new ArrayList<>();
        String[] actorNames = request.getParameterValues(reviewFields[5]);
        if (actorNames != null) {
            Collections.addAll(actors, actorNames);
        }
        List<Actor> actorList = actors.stream()
                .map(actor -> Actor.builder().name(actor).build()).toList();
        return new Review(id, country, overview, director, backdropPath,
                          rating, actorList);
    }
}
