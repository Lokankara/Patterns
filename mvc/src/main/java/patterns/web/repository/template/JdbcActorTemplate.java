package patterns.web.repository.template;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import patterns.web.exception.DataException;
import patterns.web.model.entity.Actor;
import patterns.web.repository.provider.ProxyConnection;
import patterns.web.service.mapper.result.ActorResultSetMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static java.sql.Connection.TRANSACTION_SERIALIZABLE;
import static patterns.web.repository.provider.DbManager.getConnection;

@Component
@RequiredArgsConstructor
public class JdbcActorTemplate implements Template<Actor> {

    private static final String SELECT_BY_NAME = "SELECT actor_id, name FROM actor WHERE name = ?";
    private static final String SELECT_ALL = "SELECT actor_id, name FROM actor";
    private static final String INSERT_ACTOR = "INSERT INTO actor (actor_id, name) VALUES (?, ?)";

    private final ActorResultSetMapper resultSetMapper;

    @Override
    public Optional<Actor> getByName(String name) {
        try (ProxyConnection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(SELECT_BY_NAME)) {
            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? Optional.of(resultSetMapper.mapRow(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new DataException("Failed to fetch actor by name: " + name);
        }
    }

    @Override
    public List<Actor> getAll() {
        List<Actor> actors = new ArrayList<>();
        try (ProxyConnection connection = getConnection();
             ResultSet rs = connection.prepareStatement(SELECT_ALL).executeQuery()) {
            while (rs.next()) {
                actors.add(resultSetMapper.mapRow(rs));
            }
            return actors;
        } catch (SQLException e) {
            throw new DataException("Failed to fetch all actors", e);
        }
    }

    @Override
    public Actor save(Actor actor) {
        try (ProxyConnection connection = getConnection()) {
            insertQuery(Collections.singletonList(actor), connection);
        } catch (SQLException e) {
            throw new DataException(e.getMessage());
        }
        return actor;
    }

    @Override
    public List<Actor> saveAll(List<Actor> actors) {
        try (ProxyConnection connection = getConnection()) {
            insertQuery(actors, connection);
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e);
        }
        return actors;
    }

    private void insertQuery(Collection<Actor> actors, ProxyConnection connection) throws SQLException {
        connection.setAutoCommit(false);
        connection.setTransactionIsolation(TRANSACTION_SERIALIZABLE);
        try (PreparedStatement statement = connection.prepareStatement(INSERT_ACTOR)) {
            for (Actor actor : actors) {
                statement.setObject(1, actor.getActorId());
                statement.setString(2, actor.getName());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            connection.rollback();
            throw new DataException(e.getMessage(), e);
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
