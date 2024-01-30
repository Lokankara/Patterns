package patterns.rest.repository.provider;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Component
public class DbManager {
    @Getter
    private static final DbManager instance = new DbManager();
    private static DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static ProxyConnection getConnection() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("DataSource is not properly initialized.");
        }
        return new ProxyConnection(dataSource.getConnection());
    }
}
