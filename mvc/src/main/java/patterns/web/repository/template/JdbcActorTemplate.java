package patterns.web.repository.template;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import patterns.web.exception.DataException;
import patterns.web.model.entity.Actor;
import patterns.web.repository.provider.ProxyConnection;
import patterns.web.service.mapper.result.ActorResultSetMapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static java.sql.Connection.TRANSACTION_SERIALIZABLE;
import static patterns.web.repository.provider.DbManager.getConnection;
import static patterns.web.repository.template.Query.INSERT_ACTOR;

@Component
@RequiredArgsConstructor
public class JdbcActorTemplate
        implements Template<Actor> {

    private final ActorResultSetMapper resultSetMapper;

    @Override
    public Optional<Actor> getByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Actor> getAll() {
        return null; //TODO
    }

    @Override
    public Actor save(Actor actor) {
        return null;
    }

    @Override
    public List<Actor> saveAll(List<Actor> actors) {
        try (ProxyConnection connection = getConnection()) {
            insertQuery(actors, connection);
        } catch (SQLException e) {
            throw new DataException(e.getMessage());
        }
        return actors;
    }

    private void insertQuery(
            List<Actor> actors,
            ProxyConnection connection)
            throws SQLException {
        connection.setAutoCommit(false);
        connection.setTransactionIsolation(TRANSACTION_SERIALIZABLE);
        try (PreparedStatement statement =
                     connection.prepareStatement(INSERT_ACTOR)) {
            for (Actor actor : actors) {
                statement.setObject(1, actor.getActorId());
                statement.setString(2, actor.getName());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            connection.rollback();
            throw new DataException(e.getMessage());
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
