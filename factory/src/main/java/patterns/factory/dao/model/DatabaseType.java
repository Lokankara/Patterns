package patterns.factory.dao.model;

import lombok.AllArgsConstructor;
import patterns.factory.dao.DatabaseFactory;
import patterns.factory.dao.MongoDBFactory;
import patterns.factory.dao.MySQLFactory;
import patterns.factory.dao.PostgreSQLFactory;
import patterns.factory.dao.RedisFactory;

@AllArgsConstructor
public enum DatabaseType {
    REDIS(RedisFactory.class),
    MYSQL(MySQLFactory.class),
    POSTGRESQL(PostgreSQLFactory.class),
    MONGODB(MongoDBFactory.class);

    private final Class<? extends DatabaseFactory> factoryClass;

    public DatabaseFactory getFactory() {
        try {
            return factoryClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create database factory", e);
        }
    }
}
