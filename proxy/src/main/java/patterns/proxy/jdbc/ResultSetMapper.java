package patterns.proxy.jdbc;

import jakarta.servlet.http.HttpServletRequest;

import java.sql.ResultSet;

public abstract class ResultSetMapper<T> {

    public abstract T mapRow(ResultSet resultSet);

    public abstract T mapper(HttpServletRequest request);
}
