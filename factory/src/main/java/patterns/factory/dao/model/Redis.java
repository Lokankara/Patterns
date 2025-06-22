package patterns.factory.dao.model;

import java.sql.Connection;
import java.sql.DriverManager;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import patterns.factory.dao.DatabaseConnection;
import patterns.factory.dao.DatabaseFactory;
import patterns.factory.dao.DatabaseFactoryCreator;

@Slf4j
public record Redis(String url, String user, String password) implements DatabaseConnection {
    @SneakyThrows
    @Override
    public Connection connect() {
        DatabaseFactory factory = DatabaseFactoryCreator.getFactory(DatabaseType.REDIS);
        Connection redisConnection = DriverManager.getConnection("redis://localhost:6379", "user", "password");
        log.info("Connecting to {} database... {} {}", this.getClass().getSimpleName(), factory, redisConnection);
        return redisConnection;
    }
}
