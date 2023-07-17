package patterns.refactoring.command.filter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
