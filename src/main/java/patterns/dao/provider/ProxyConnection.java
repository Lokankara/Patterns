package patterns.dao.provider;

import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@RequiredArgsConstructor
public class ProxyConnection
        implements AutoCloseable {
    private final Connection connection;

    public PreparedStatement prepareStatement(String sql)
            throws SQLException {
        return connection.prepareStatement(sql);
    }

    public void rollback()
            throws SQLException {
        if (connection != null) {
            connection.rollback();
        }
    }

    @Override
    public void close()
            throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public void setTransactionIsolation(int level)
            throws SQLException {
        connection.setTransactionIsolation(level);
    }

    public void setAutoCommit(boolean autoCommit)
            throws SQLException {
        connection.setAutoCommit(autoCommit);
    }

    public void commit()
            throws SQLException {
        connection.commit();
    }
}
