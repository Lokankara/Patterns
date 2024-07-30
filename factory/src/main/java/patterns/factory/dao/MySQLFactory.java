package patterns.factory.dao;

public class MySQLFactory implements DatabaseFactory {
    @Override
    public DatabaseConnection createConnection(String url, String user, String password) {
        return new MySQL(url, user, password);
    }
}
