package patterns.web.filter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface FilterCommand {

    boolean getAccess(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException;
}
