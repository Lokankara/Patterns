package patterns.factory.dao.model;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import patterns.factory.dao.DatabaseFactory;
import patterns.factory.dao.MongoDBFactory;
import patterns.factory.dao.MySQLFactory;
import patterns.factory.dao.PostgreSQLFactory;
import patterns.factory.dao.RedisFactory;

import javax.sql.DataSource;
import java.lang.reflect.Constructor;

@AllArgsConstructor
public enum DatabaseType {
    REDIS(RedisFactory.class),
    MYSQL(MySQLFactory.class),
    POSTGRESQL(PostgreSQLFactory.class),
    MONGODB(MongoDBFactory.class);

    private final Class<? extends DatabaseFactory> factoryClass;

    public DatabaseFactory getFactory() {
        try {
            return factoryClass.getDeclaredConstructor(DataSource.class).newInstance(new SimpleDriverDataSource());
        } catch (Exception e) {
            throw new RuntimeException("Failed to create database factory", e);
        }
    }
}