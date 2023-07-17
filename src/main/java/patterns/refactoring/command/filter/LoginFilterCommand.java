package patterns.refactoring.command.filter;

import patterns.refactoring.model.entity.Customer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilterCommand
        implements FilterCommand {
    @Override
    public boolean getAccess(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Customer customer = (Customer) httpSession.getAttribute("user");
        return customer == null;
    }
}
