package patterns.proxy;

import patterns.factory.dao.DatabaseFactory;
import patterns.factory.dao.DatabaseFactoryCreator;
import patterns.factory.dao.model.DatabaseType;

public class DatabaseFactoryProxy {
    private DatabaseFactory realFactory;
    private final DatabaseType databaseType;

    public DatabaseFactoryProxy(DatabaseType databaseType) {
        this.databaseType = databaseType;
    }

    private DatabaseFactory getRealFactory() {
        if (realFactory == null) {
            realFactory = DatabaseFactoryCreator.getFactory(databaseType);
        }
        return realFactory;
    }
}
