package patterns.factory.dao.model;

import lombok.extern.slf4j.Slf4j;
import patterns.factory.dao.DatabaseConnection;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public record MySQL(String url, String user, String password, DataSource dataSource) implements DatabaseConnection {

    @Override
    public Connection connect() {
        try {
            return dataSource.getConnection(user, password);
        } catch (SQLException e) {
            log.error("Failed to connect to MySQL database: {}", url, e);
            throw new RuntimeException("MySQL connection failed", e);
        }
    }
}
