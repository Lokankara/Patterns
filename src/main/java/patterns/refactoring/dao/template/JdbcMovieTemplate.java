package patterns.refactoring.dao.template;

import lombok.RequiredArgsConstructor;
import patterns.refactoring.dao.mapper.result.MovieResultSetMapper;
import patterns.refactoring.dao.provider.ProxyConnection;
import patterns.refactoring.exception.DataException;
import patterns.refactoring.model.entity.Movie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.sql.Connection.TRANSACTION_READ_COMMITTED;
import static java.sql.Connection.TRANSACTION_SERIALIZABLE;
import static patterns.refactoring.dao.provider.DbManager.getConnection;
import static patterns.refactoring.dao.template.Query.INSERT_MOVIE;
import static patterns.refactoring.dao.template.Query.SELECT_ALL_MOVIE;
import static patterns.refactoring.dao.template.Query.SELECT_BY_TITLE;

@RequiredArgsConstructor
public class JdbcMovieTemplate
        implements Template<Movie> {
    private final MovieResultSetMapper resultSetMapper;

    public JdbcMovieTemplate() {
        this.resultSetMapper = new MovieResultSetMapper();
    }

    @Override
    public Optional<Movie> getByName(String title) {
        try (ProxyConnection connection =
                     getConnection()) {
            return selectQuery(title, connection);
        } catch (SQLException e) {
            throw new DataException(e.getMessage());
        }
    }

    @Override
    public List<Movie> getAll() {
        try (ProxyConnection connection =
                     getConnection()) {
            return selectQuery(connection);
        } catch (SQLException e) {
            throw new DataException(e.getMessage());
        }
    }

    @Override
    public Movie save(Movie movie) {
        try (ProxyConnection connection =
                     getConnection()) {
            insertQuery(movie, connection);
        } catch (SQLException e) {
            throw new DataException(e.getMessage());
        }
        return movie;
    }

    @Override
    public List<Movie> saveAll(List<Movie> movies) {
        try (ProxyConnection connection =
                     getConnection()) {
            insertQuery(movies, connection);
        } catch (SQLException e) {
            throw new DataException(e.getMessage());
        }
        return movies;
    }

    private void insertQuery(
            List<Movie> movies,
            ProxyConnection connection)
            throws SQLException {
        connection.setAutoCommit(false);
        connection.setTransactionIsolation(TRANSACTION_SERIALIZABLE);
        try (PreparedStatement statement =
                     connection.prepareStatement(INSERT_MOVIE)) {
            for (Movie movie : movies) {
                statement.setObject(1, movie.getMovieId());
                statement.setString(2, movie.getTitle());
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
            Movie movie,
            ProxyConnection connection)
            throws SQLException {
        try (PreparedStatement statement =
                     connection.prepareStatement(INSERT_MOVIE)) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(TRANSACTION_SERIALIZABLE);
            statement.setObject(1, movie.getMovieId());
            statement.setString(2, movie.getTitle());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new DataException(e.getMessage());
        } finally {
            connection.setAutoCommit(true);
        }
    }

    private ArrayList<Movie> selectQuery(
            ProxyConnection connection)
            throws SQLException {
        PreparedStatement statement =
                connection.prepareStatement(SELECT_ALL_MOVIE);
        Set<Movie> movies = new HashSet<>();
        try (ResultSet resultSet = statement.executeQuery()) {
            connection.setTransactionIsolation(TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(false);
            while (resultSet.next()) {
                movies = resultSetMapper.mapAll(resultSet, new HashSet<>());
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new DataException(e.getMessage());
        } finally {
            connection.setAutoCommit(true);
        }
        return new ArrayList<>(movies);
    }

    private Optional<Movie> selectQuery(
            String title,
            ProxyConnection connection)
            throws SQLException {
        Optional<Movie> movie;
        try (PreparedStatement statement =
                     connection.prepareStatement(SELECT_BY_TITLE)) {
            connection.setTransactionIsolation(TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(false);
            statement.setString(1, title);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Movie> movies = new ArrayList<>();
                while (resultSet.next()) {
                    movies.add(resultSetMapper.mapRow(resultSet));
                }
                movie = resultSetMapper.join(movies);
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new DataException(e.getMessage());
        } finally {
            connection.setAutoCommit(true);
        }
        return movie;
    }
}
