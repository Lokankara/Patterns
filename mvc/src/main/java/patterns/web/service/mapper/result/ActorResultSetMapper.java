package patterns.web.service.mapper.result;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import patterns.web.exception.DataException;
import patterns.web.model.entity.Actor;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@Component
public class ActorResultSetMapper extends ResultSetMapper<Actor> {

    @Override
    public Actor mapRow(ResultSet resultSet) {
        try {
            return new Actor(resultSet.getLong("actor_id"), resultSet.getString("actor_name"));
        } catch (SQLException e) {
            log.warn(e.getMessage());
            throw new DataException(e.getMessage());
        }
    }

    @Override
    Actor mapper(HttpServletRequest request) {
        String actorIdParam = request.getParameter("actorId");
        Long actorId = actorIdParam != null ? Long.parseLong(actorIdParam) : 0L;
        return new Actor(actorId, request.getParameter("name"));
    }
}
