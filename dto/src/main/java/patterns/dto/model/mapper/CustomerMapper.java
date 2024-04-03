package patterns.dto.model.mapper;

import patterns.dto.model.dto.Login;
import patterns.dto.model.entity.Customer;

public class CustomerMapper {

    private CustomerMapper() {
    }

    public static Login toLogin(Customer user) {
        Login login = new Login();
        login.setLogin(user.getName());
        login.setPassword(user.getSurname());
        login.setTimestamp(user.getTimestamp());
        return login;
    }

    public static Customer toUser(Login login) {
        Customer user = new Customer();
        user.setName(login.getLogin());
        user.setSurname(login.getPassword());
        user.setTimestamp(login.getTimestamp());
        return user;
    }
}
