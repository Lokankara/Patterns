package patterns.factory.dao;

import lombok.AllArgsConstructor;
import patterns.factory.dao.model.MongoDB;

import javax.sql.DataSource;

@AllArgsConstructor
public class MongoDBFactory implements DatabaseFactory {
    private final DataSource dataSource;

    @Override
    public DatabaseConnection createConnection(String url, String user, String password) {
        return new MongoDB(url, user, password);
    }
}
