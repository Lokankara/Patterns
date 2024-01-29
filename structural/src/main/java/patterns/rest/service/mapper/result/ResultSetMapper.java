package patterns.rest.service.mapper.result;

import jakarta.servlet.http.HttpServletRequest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public abstract class ResultSetMapper<T> {
    abstract T mapRow(ResultSet resultSet);

    abstract T mapper(HttpServletRequest request);

    public Set<T> mapAll(
            ResultSet resultSet,
            Set<T> elements)
            throws SQLException {

        if (!resultSet.next()) {
            return elements;
        } else {
            elements.add(mapRow(resultSet));
            return mapAll(resultSet, elements);
        }
    }
}
