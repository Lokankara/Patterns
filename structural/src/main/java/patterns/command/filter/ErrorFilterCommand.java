package patterns.command.filter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ErrorFilterCommand
        implements FilterCommand {
    @Override
    public boolean getAccess(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        return true;
    }
}
