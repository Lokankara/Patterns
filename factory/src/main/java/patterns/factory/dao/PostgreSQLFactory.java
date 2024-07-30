package patterns.factory.dao;

public class PostgreSQLFactory implements DatabaseFactory {
    @Override
    public DatabaseConnection createConnection(String url, String user, String password) {
        return new PostgreSQL(url, user, password);
    }
}
