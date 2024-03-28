package patterns.web.pages;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ErrorCommand
        implements Command {
    @Override
    public String execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {
        return "error";
    }
}
