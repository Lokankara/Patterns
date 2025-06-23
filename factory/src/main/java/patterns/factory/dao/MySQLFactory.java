package patterns.factory.dao;

import lombok.RequiredArgsConstructor;
import patterns.factory.dao.model.MySQL;

import javax.sql.DataSource;

@RequiredArgsConstructor
public class MySQLFactory implements DatabaseFactory {
    private final DataSource dataSource;

    @Override
    public DatabaseConnection createConnection(String url, String user, String password) {
        return new MySQL(url, user, password, dataSource);
    }
}