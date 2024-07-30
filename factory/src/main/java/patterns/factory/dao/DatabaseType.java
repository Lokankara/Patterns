package patterns.factory.dao;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DatabaseType {
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
