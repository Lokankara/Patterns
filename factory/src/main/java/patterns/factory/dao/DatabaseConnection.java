package patterns.factory.dao;

import java.sql.Connection;

public interface DatabaseConnection {
    Connection connect();

    String url();

    String user();

    String password();
}
