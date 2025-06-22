package patterns.web.exception;

import java.sql.SQLException;

public class DataException extends RuntimeException {
    public DataException(String message) {
        super(message);
    }

    public DataException(String message, SQLException e) {
        super(message, e);
    }
}
