package patterns.rest.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceConfig {

    @Value("${spring.datasource.url}")
    private String DB_URL;

    @Value("${spring.datasource.driver-class-name}")
    private String DRIVER;

    @Value("${spring.datasource.username}")
    private String DB_NAME;

    @Value("${spring.datasource.password}")
    private String DB_PASSWORD;

    @Bean
    @Primary
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(DB_NAME);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setDriverClassName(DRIVER);
        return dataSource;
    }
}
