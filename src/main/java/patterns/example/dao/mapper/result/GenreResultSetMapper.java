package patterns.example.dao.mapper.result;

import patterns.example.exception.DataException;
import patterns.example.exception.GenreNotFoundException;
import patterns.example.model.entity.Genre;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

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
