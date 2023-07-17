package patterns.example.dao.template;

import patterns.example.dao.mapper.result.ReviewResultSetMapper;
import patterns.example.dao.provider.ProxyConnection;
import patterns.example.exception.DataException;
import patterns.example.model.entity.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static java.sql.Connection.TRANSACTION_SERIALIZABLE;
import static patterns.example.dao.provider.DbManager.getConnection;
import static patterns.example.dao.template.Query.INSERT_REVIEW;

public class JdbcReviewTemplate
        implements Template<Review> {

    private final ReviewResultSetMapper resultSetMapper;

    public JdbcReviewTemplate() {
        this.resultSetMapper = new ReviewResultSetMapper();
    }

    @Override
    public Optional<Review> getByName(String name) {
        return Optional.empty(); //TODO
    }

    @Override
    public List<Review> getAll() {
        return null;
    }

    @Override
    public Review save(Review review) {
        try (ProxyConnection connection = getConnection()) {
            insertQuery(review, connection);

        } catch (SQLException e) {
            throw new DataException(e.getMessage());
        }
        return review;
    }

    @Override
    public List<Review> saveAll(List<Review> reviews) {
        try (ProxyConnection connection =
                     getConnection()) {
            insertQuery(reviews, connection);
        } catch (SQLException e) {
            throw new DataException(e.getMessage());
        }
        return reviews;
    }

    private void insertQuery(
            Review review,
            ProxyConnection connection)
            throws SQLException {
        try (PreparedStatement statement =
                     connection.prepareStatement(INSERT_REVIEW)) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(TRANSACTION_SERIALIZABLE);
            statement.setLong(1, review.getReviewId());
            statement.setString(2, review.getCountry());
            statement.setString(3, review.getOverview());
            statement.setString(4, review.getBackdropPath());
            statement.setDouble(5, review.getRating());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new DataException(e.getMessage());
        } finally {
            connection.setAutoCommit(true);
        }
    }

    private void insertQuery(
            List<Review> reviews,
            ProxyConnection connection)
            throws SQLException {
        connection.setAutoCommit(false);
        connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        try (PreparedStatement statement =
                     connection.prepareStatement(INSERT_REVIEW)) {
            for (Review review : reviews) {
                statement.setObject(1, review.getReviewId());
                statement.setString(2, review.getCountry());
                statement.setString(3, review.getOverview());
                statement.setString(4, review.getBackdropPath());
                statement.setDouble(5, review.getRating());
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
