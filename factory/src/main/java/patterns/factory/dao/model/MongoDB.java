package patterns.factory.dao.model;

import lombok.extern.slf4j.Slf4j;
import patterns.factory.dao.DatabaseConnection;

import java.sql.Connection;

@Slf4j
public record MongoDB(String url, String user, String password) implements DatabaseConnection {
    @Override
    public Connection connect() {
        log.info("Connecting to {} database...", this.getClass().getSimpleName());
        return null;
    }
}
