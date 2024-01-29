package patterns.rest.service.mapper.result;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import patterns.rest.exception.DataException;
import patterns.rest.model.entity.Actor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

@Component
public class ActorResultSetMapper
        extends ResultSetMapper<Actor> {
    private static final Logger log = Logger.getLogger(ActorResultSetMapper.class.getName());

    @Override
    Actor mapRow(ResultSet resultSet) {
        try {
            Long actorId = resultSet.getLong("actor_id");
            String name = resultSet.getString("actor_name");
            return new Actor(actorId, name);
        } catch (SQLException e) {
            log.warning(e.getMessage());
            throw new DataException(e.getMessage());
        }

    }

    @Override
    Actor mapper(HttpServletRequest request) {
        String actorIdParam = request.getParameter("actorId");
        Long actorId = actorIdParam != null ? Long.parseLong(actorIdParam) : 0L;
        String name = request.getParameter("name");
        return new Actor(actorId, name);
    }
}
