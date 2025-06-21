package patterns.factory.dao;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import patterns.factory.dao.model.DatabaseType;
import patterns.factory.dao.model.MongoDB;
import patterns.factory.dao.model.MySQL;
import patterns.factory.dao.model.PostgreSQL;

class DatabaseFactoryCreatorTest {

    @Test
    void testMySQLFactoryCreation() {
        DatabaseFactory factory = DatabaseFactoryCreator.getFactory(DatabaseType.MYSQL);
        assertNotNull(factory, "Factory should not be null");
        DatabaseConnection connection = factory.createConnection("jdbc:mysql://localhost:3306/mydb", "root", "password");

        assertInstanceOf(MySQL.class, connection, "Connection should be an instance of MySQLConnection");
        assertEquals("jdbc:mysql://localhost:3306/mydb", connection.url(), "URL should match");
        assertEquals("root", connection.user(), "User should match");
        assertEquals("password", connection.password(), "Password should match");
    }

    @Test
    void testPostgreSQLFactoryCreation() {
        DatabaseFactory factory = DatabaseFactoryCreator.getFactory(DatabaseType.POSTGRESQL);
        assertNotNull(factory, "Factory should not be null");
        DatabaseConnection connection = factory.createConnection("jdbc:postgresql://localhost:5432/mydb", "admin", "root");

        assertInstanceOf(PostgreSQL.class, connection, "Connection should be an instance of PostgreSQLConnection");
        assertEquals("jdbc:postgresql://localhost:5432/mydb", connection.url(), "URL should match");
        assertEquals("admin", connection.user(), "User should match");
        assertEquals("root", connection.password(), "Password should match");
    }

    @Test
    void testMongoDBFactoryCreation() {
        DatabaseFactory factory = DatabaseFactoryCreator.getFactory(DatabaseType.MONGODB);
        assertNotNull(factory, "Factory should not be null");

        DatabaseConnection connection = factory.createConnection("mongodb://localhost:27017/mydb", "mongoUser", "mongoPass");
        assertInstanceOf(MongoDB.class, connection, "Connection should be an instance of MongoDBConnection");
        assertEquals("mongodb://localhost:27017/mydb", connection.url(), "URL should match");
        assertEquals("mongoUser", connection.user(), "User should match");
        assertEquals("mongoPass", connection.password(), "Password should match");
    }

    @Test
    void testInvalidDatabaseType() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> DatabaseFactoryCreator.getFactory(null));

        assertEquals("Database type cannot be null", thrown.getMessage());
    }
}
