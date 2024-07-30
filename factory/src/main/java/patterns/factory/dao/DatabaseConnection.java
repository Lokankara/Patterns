package patterns.factory.dao;

public interface DatabaseConnection {
    void connect();

    String url();

    String user();

    String password();
}
