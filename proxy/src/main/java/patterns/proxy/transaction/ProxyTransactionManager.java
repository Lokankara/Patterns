package patterns.proxy.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import org.springframework.jdbc.datasource.DataSourceUtils;

@Component
@RequiredArgsConstructor
public class ProxyTransactionManager {

    private final DataSource dataSource;

    public Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }

    public void begin(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
    }

    public void commit(Connection connection) throws SQLException {
        connection.commit();
        connection.setAutoCommit(true);
        DataSourceUtils.releaseConnection(connection, dataSource);
    }

    public void rollback(Connection connection) {
        try {
            connection.rollback();
            connection.setAutoCommit(true);
        } catch (SQLException ignore) {
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }
}
