package patterns.command.pages;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.logging.Logger;

public class LoginCommand
        implements Command {
    private static final Logger log = Logger.getLogger(LoginCommand.class.getName());

    @Override
    public String execute(
            HttpServletRequest request,
            HttpServletResponse response) {
        Object errorPage = request.getSession().getAttribute("errorPage");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        log.info(email + password);
        log.warning(errorPage.toString());
        return "login";
    }
}

