package patterns.proxy.customer;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import patterns.proxy.jdbc.ResultSetMapper;
import patterns.proxy.transaction.DataException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CustomerResultSetMapper extends ResultSetMapper<Customer> {

    private static final String[] USER_FIELDS = {"id", "login", "password", "email", "salt", "active"};

    @Override
    public Customer mapRow(ResultSet resultSet) {
        try {
            return Customer.builder()
                    .id(resultSet.getLong("id"))
                    .login(resultSet.getString("login"))
                    .password(resultSet.getString("password"))
                    .email(resultSet.getString("email"))
                    .salt(resultSet.getString("salt"))
                    .active(resultSet.getBoolean("active"))
                    .build();
        } catch (SQLException e) {
            log.warn("Failed to map row: {}", e.getMessage());
            throw new DataException("Error mapping ResultSet to Customer", e);
        }
    }

    @Override
    public Customer mapper(HttpServletRequest request) {
        Map<String, String> params = Arrays.stream(USER_FIELDS)
                .filter(key -> request.getParameter(key) != null && !request.getParameter(key).isBlank())
                .collect(Collectors.toMap(key -> key, request::getParameter));

        return Customer.builder()
                .id(parseLong(params.get("id")))
                .login(params.get("login"))
                .password(params.get("password"))
                .email(params.get("email"))
                .salt(params.get("salt"))
                .active(Boolean.parseBoolean(params.getOrDefault("active", "false")))
                .build();
    }

    private Long parseLong(String value) {
        try {
            return value != null ? Long.parseLong(value) : 0L;
        } catch (NumberFormatException e) {
            log.warn("Invalid id value: {}", value);
            return 0L;
        }
    }
}
