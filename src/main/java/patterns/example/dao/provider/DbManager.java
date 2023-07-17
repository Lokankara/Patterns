package patterns.example.dao.provider;

import org.apache.commons.dbcp2.BasicDataSource;
import patterns.example.exception.DataException;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class DbManager {
    private Properties properties;
    private static final DbManager instance = new DbManager();
    private static DataSource dataSource;
    private static final String DB_URL = "db.url";
    private static final String DRIVER = "db.driver";
    private static final String DB_NAME = "db.username";
    private static final String SIZE_POOL = "db.maxTotal";
    private static final String INIT_SIZE = "db.initSize";
    private static final String DB_PASSWORD = "db.password";
    private static final String FILE_PROPS = "src/main/resources/application.properties";

    private DbManager() {
        BasicDataSource source = new BasicDataSource();
        loadProperties();
        source.setUrl(getDBUrl());
        source.setUsername(getDBName());
        source.setPassword(getDBPassword());
        source.setDriverClassName(getDriver());
        source.setMaxTotal(getMaxTotal());
        source.setInitialSize(getInitialSize());
        dataSource = source;
    }

    public DbManager instance() {
        return instance;
    }

    public static ProxyConnection getConnection()
            throws SQLException {
        return new ProxyConnection(dataSource.getConnection());
    }

    private void loadProperties() {
        properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream(FILE_PROPS)) {
            properties.load(inputStream);
        } catch (IOException exception) {
            throw new DataException(exception.getMessage());
        }
    }

    private String getDBUrl() {
        return properties.getProperty(DB_URL);
    }

    private String getDBName() {
        return properties.getProperty(DB_NAME);
    }

    private String getDBPassword() {
        return properties.getProperty(DB_PASSWORD);
    }

    private int getMaxTotal() {
        return Integer.parseInt(properties.getProperty(SIZE_POOL));
    }

    private String getDriver() {
        return properties.getProperty(DRIVER);
    }

    private int getInitialSize() {
        return Integer.parseInt(properties.getProperty(INIT_SIZE));
    }
}
