package patterns.rest.repository.template;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import patterns.rest.exception.DataException;
import patterns.rest.model.entity.Genre;
import patterns.rest.repository.provider.ProxyConnection;
import patterns.rest.service.mapper.result.GenreResultSetMapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static java.sql.Connection.TRANSACTION_SERIALIZABLE;
import static patterns.rest.repository.provider.DbManager.getConnection;
import static patterns.rest.repository.template.Query.INSERT_GENRE;

@Component
@RequiredArgsConstructor
public class JdbcGenreTemplate
        implements Template<Genre> {

    private final GenreResultSetMapper resultSetMapper;

    @Override
    public Optional<Genre> getByName(String name) {
        return Optional.empty(); //TODO
    }

    @Override
    public List<Genre> getAll() {
        return null;
    }

    @Override
    public Genre save(Genre genre) {
        try (ProxyConnection connection = getConnection()) {
            insertQuery(genre, connection);
        } catch (SQLException e) {
            throw new DataException(e.getMessage());
        }
        return genre;
    }

    @Override
    public List<Genre> saveAll(List<Genre> genres) {
        try (ProxyConnection connection = getConnection()) {
            insertQuery(genres, connection);
        } catch (SQLException e) {
            throw new DataException(e.getMessage());
        }
        return genres;
    }

    private void insertQuery(
            List<Genre> genres,
            ProxyConnection connection)
            throws SQLException {
        connection.setAutoCommit(false);
        connection.setTransactionIsolation(TRANSACTION_SERIALIZABLE);
        try (PreparedStatement statement = connection.prepareStatement(
                INSERT_GENRE)) {
            for (Genre genre : genres) {
                statement.setObject(1, genre.getGenreId());
                statement.setString(2, genre.getName());
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


    private void insertQuery(
            Genre genre,
            ProxyConnection connection)
            throws SQLException {
        connection.setAutoCommit(false);
        connection.setTransactionIsolation(TRANSACTION_SERIALIZABLE);
        try (PreparedStatement statement = connection.prepareStatement(
                INSERT_GENRE)) {
            statement.setObject(1, genre.getGenreId());
            statement.setString(2, genre.getName());
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new DataException(e.getMessage());
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
