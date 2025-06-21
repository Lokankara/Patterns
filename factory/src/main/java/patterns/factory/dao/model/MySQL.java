package patterns.factory.dao.model;

import lombok.extern.slf4j.Slf4j;
import patterns.factory.dao.DatabaseConnection;

@Slf4j
public record MySQL(String url, String user, String password) implements DatabaseConnection {

    @Override
    public void connect() {
        log.info("Connecting to {} database...", this.getClass().getSimpleName());
    }
}
