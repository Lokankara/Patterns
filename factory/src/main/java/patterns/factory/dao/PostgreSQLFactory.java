package patterns.factory.dao;

import patterns.factory.dao.model.PostgreSQL;

public class PostgreSQLFactory implements DatabaseFactory {
    @Override
    public DatabaseConnection createConnection(String url, String user, String password) {
        return new PostgreSQL(url, user, password);
    }
}
