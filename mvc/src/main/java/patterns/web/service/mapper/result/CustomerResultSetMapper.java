package patterns.web.service.mapper.result;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import patterns.web.exception.DataException;
import patterns.web.model.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CustomerResultSetMapper
        extends ResultSetMapper<Customer> {

    private static final String[] userFields = {"id", "login", "password", "email", "salt", "active"};

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
            log.warn("Error mapping customer row", e);
            throw new DataException("Mapping error", e);
        }
    }

    @Override
    Customer mapper(HttpServletRequest request) {
        Map<String, String> params = Arrays.stream(userFields)
                .filter(key -> !"".equals(request.getParameter(key)) && request.getParameter(
                        key) != null)
                .collect(Collectors.toMap(key -> key,
                                          request::getParameter,
                                          (a, b) -> b));
        return Customer.builder().build();
    }
}
