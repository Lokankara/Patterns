package patterns.factory.dao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public record MySQL(String url, String user, String password) implements DatabaseConnection {

    @Override
    public void connect() {
        log.info("Connecting to {} database...", this.getClass().getSimpleName());
    }
}
