package patterns.factory.dao;

public class DatabaseFactoryCreator {

    public static DatabaseFactory getFactory(DatabaseType databaseType) {
        if (databaseType == null) {
            throw new IllegalArgumentException("Database type cannot be null");
        }
        return databaseType.getFactory();
    }
}
