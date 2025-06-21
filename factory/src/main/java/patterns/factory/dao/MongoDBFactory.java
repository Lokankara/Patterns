package patterns.factory.dao;

import patterns.factory.dao.model.MongoDB;

public class MongoDBFactory implements DatabaseFactory {
    @Override
    public DatabaseConnection createConnection(String url, String user, String password) {
        return new MongoDB(url, user, password);
    }
}
