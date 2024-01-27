package patterns.command.filter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface FilterCommand {

    boolean getAccess(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException;
}
