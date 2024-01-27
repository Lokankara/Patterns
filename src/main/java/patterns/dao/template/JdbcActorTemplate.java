package patterns.dao.template;

import patterns.dao.mapper.result.ActorResultSetMapper;
import patterns.dao.provider.ProxyConnection;
import patterns.exception.DataException;
import patterns.model.entity.Actor;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static java.sql.Connection.TRANSACTION_SERIALIZABLE;
import static patterns.dao.provider.DbManager.getConnection;
import static patterns.dao.template.Query.INSERT_ACTOR;

public class JdbcActorTemplate
        implements Template<Actor> {
    private final ActorResultSetMapper resultSetMapper;

    public JdbcActorTemplate() {
        this.resultSetMapper = new ActorResultSetMapper();
    }

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
