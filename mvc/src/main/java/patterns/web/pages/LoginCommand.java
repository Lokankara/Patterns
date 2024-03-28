package patterns.web.pages;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginCommand
        implements Command {

    @Override
    public String execute(
            HttpServletRequest request,
            HttpServletResponse response) {
        Object errorPage = request.getSession().getAttribute("errorPage");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        log.info("{} {}", email, password);
        log.warn(errorPage.toString());
        return "login";
    }
}

