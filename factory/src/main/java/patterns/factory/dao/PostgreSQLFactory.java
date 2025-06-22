package patterns.factory.dao;

import lombok.AllArgsConstructor;
import patterns.factory.dao.model.PostgreSQL;

import javax.sql.DataSource;

@AllArgsConstructor
public class PostgreSQLFactory implements DatabaseFactory {

    private final DataSource dataSource;

    @Override
    public DatabaseConnection createConnection(String url, String user, String password) {
        return new PostgreSQL(url, user, password, dataSource);
    }
}
