package patterns.factory.dao.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;
import patterns.factory.dao.DatabaseConnection;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@Slf4j
public class PostgreSQL implements DatabaseConnection {

    private final String url;
    private final String user;
    private final String password;
    private final DataSource dataSource;

    public PostgreSQL(String url, String user, String password, DataSource dataSource) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.dataSource = dataSource;
    }

    @Override
    public Connection connect() {
        return DataSourceUtils.getConnection(dataSource);
    }

    @Override
    public String url() {
        return url;
    }

    @Override
    public String user() {
        return user;
    }

    @Override
    public String password() {
        return password;
    }
}
