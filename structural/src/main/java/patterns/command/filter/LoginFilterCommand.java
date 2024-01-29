package patterns.command.filter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import patterns.rest.model.entity.Customer;

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
