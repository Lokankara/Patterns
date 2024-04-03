package patterns.dto.mapper;

import org.junit.jupiter.api.Test;
import patterns.dto.model.dto.Login;
import patterns.dto.model.entity.Customer;
import patterns.dto.model.mapper.CustomerMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerMapperTest {

    @Test
    void testToLogin() {
        Customer user = new Customer();
        user.setName("Jack");
        user.setSurname("Sparrow");
        user.setTimestamp(10L);

        Login login = CustomerMapper.toLogin(user);

        assertEquals("Jack", login.getLogin());
        assertEquals("Sparrow", login.getPassword());
        assertEquals(user.getTimestamp(), login.getTimestamp());
    }

    @Test
    void testToUser() {
        Login login = new Login();
        login.setLogin("William");
        login.setPassword("Turner");
        login.setTimestamp(10L);

        Customer user = CustomerMapper.toUser(login);

        assertEquals("William", user.getName());
        assertEquals("Turner", user.getSurname());
        assertEquals(login.getTimestamp(), user.getTimestamp());
    }
}
