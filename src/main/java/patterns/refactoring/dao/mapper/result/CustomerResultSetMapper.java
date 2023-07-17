package patterns.refactoring.dao.mapper.result;

import patterns.refactoring.model.entity.Customer;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomerResultSetMapper
        extends ResultSetMapper<Customer> {

    private static final String[] userFields = {"id", "login", "password", "email", "salt", "active"};

    @Override
    Customer mapRow(ResultSet resultSet) {
        return Customer.builder().build();
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
