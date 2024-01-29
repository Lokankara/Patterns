package patterns.rest.service.mapper.result;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import patterns.rest.exception.DataException;
import patterns.rest.exception.GenreNotFoundException;
import patterns.rest.model.entity.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

@Component
public class GenreResultSetMapper
        extends ResultSetMapper<Genre> {

    private static final Logger log = Logger.getLogger(GenreResultSetMapper.class.getName());
    @Override
    public Genre mapRow(ResultSet resultSet) {
        try {
            String name = resultSet.getString("genre_name");
            if (name != null) {
                return Genre.builder().name(name).build();
            }
        } catch (SQLException e) {
            log.warning(e.getMessage());
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
