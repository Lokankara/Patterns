package patterns.factory.dao;

public interface DatabaseFactory {
    DatabaseConnection createConnection(String url, String user, String password);
}
