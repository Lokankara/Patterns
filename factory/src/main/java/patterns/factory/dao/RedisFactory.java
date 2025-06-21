package patterns.factory.dao;

import patterns.factory.dao.model.Redis;

public class RedisFactory implements DatabaseFactory {
    @Override
    public DatabaseConnection createConnection(String url, String user, String password) {
        return new Redis(url, user, password);
    }
}
