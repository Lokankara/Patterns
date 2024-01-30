package patterns.rest.service.mapper.result;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import patterns.rest.exception.DataException;
import patterns.rest.exception.GenreNotFoundException;
import patterns.rest.model.entity.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@Component
public class GenreResultSetMapper
        extends ResultSetMapper<Genre> {

    @Override
    public Genre mapRow(ResultSet resultSet) {
        try {
            String name = resultSet.getString("genre_name");
            if (name != null) {
                return Genre.builder().name(name).build();
            }
        } catch (SQLException e) {
            log.warn(e.getMessage());
            throw new DataException(e.getMessage());
        }
        throw new GenreNotFoundException("Genre Not Found");
    }

    @Override
    public Genre mapper(HttpServletRequest request) {
        String name = request.getParameter("name");
        if (name != null && !name.isEmpty()) {
            return Genre.builder().name(name).build();
        } else {
            throw new GenreNotFoundException("Genre Not Found");
        }
    }
}
