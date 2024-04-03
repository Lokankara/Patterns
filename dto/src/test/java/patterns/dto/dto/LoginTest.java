package patterns.dto.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import patterns.dto.model.dto.Login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class LoginTest {

    private Login login;

    @BeforeEach
    void setUp() {
        login = new Login();
        login.setLogin("William");
        login.setPassword("Turner");
        login.setTimestamp(10L);
    }

    @Test
    void testGetLogin() {
        assertEquals("William", login.getLogin());
    }

    @Test
    void testSetLogin() {
        login.setLogin("NewLogin");
        assertEquals("NewLogin", login.getLogin());
    }

    @Test
    void testGetPassword() {
        assertEquals("Turner", login.getPassword());
    }

    @Test
    void testGetTimestamp() {
        assertEquals(10L, (long) login.getTimestamp());
    }

    @Test
    void testToString() {
        String expectedToString = "{login: William, password: Turner, timestamp: 10}";
        assertEquals(expectedToString, login.toString());
    }

    @Test
    void testEquals() {
        Login will = new Login("https", "William", "Turner", 10L);

        assertEquals(login, will);
    }

    @Test
    void testNotEquals() {
        Login jack = new Login();
        jack.setLogin("Jack");
        jack.setPassword("Sparrow");
        jack.setTimestamp(20L);

        assertNotEquals(login, jack);
    }

    @Test
    void testEqualsWithNullObject() {
        assertNotEquals(null, login);
    }

    @Test
    void testEqualsWithSameObject() {
        assertEquals(login, login);
    }

    @Test
    void testEqualsWithDifferentClass() {
        assertNotEquals(login, login.toString());
    }

    @Test
    void testHashCode() {
        Login will = new Login();
        will.setLogin("William");
        will.setPassword("Turner");
        will.setTimestamp(10L);

        Login jack = new Login();
        jack.setLogin("Jack");
        jack.setPassword("Sparrow");
        jack.setTimestamp(20L);

        assertEquals(login.hashCode(), will.hashCode());
        assertNotEquals(login.hashCode(), jack.hashCode());
    }
}
