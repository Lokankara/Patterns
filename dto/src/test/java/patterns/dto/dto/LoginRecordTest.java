package patterns.dto.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import patterns.dto.model.dto.LoginRecord;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class LoginRecordTest {

    private LoginRecord login;

    @BeforeEach
    void setUp() {
        login = new LoginRecord("William", "Turner", 10L);
    }

    @Test
    void testGetLogin() {
        assertEquals("William", login.login());
    }

    @Test
    void testGetPassword() {
        assertEquals("Turner", login.password());
    }

    @Test
    void testGetTimestamp() {
        assertEquals(10L, (long) login.timestamp());
    }

    @Test
    void testToString() {
        String expectedToString = "{login: William, password: Turner, timestamp: 10}";
        assertEquals(expectedToString, login.toString());
    }

    @Test
    void testEquals() {
        LoginRecord will = new LoginRecord("William", "Turner", 10L);
        LoginRecord jack = new LoginRecord("Jack", "Sparrow", 20L);

        assertEquals(login, will);
        assertNotEquals(login, jack);
    }

    @Test
    void testNotNullEquals() {
        assertNotEquals(login, null);
    }

    @Test
    void testHashCode() {
        LoginRecord will = new LoginRecord("William", "Turner", 10L);
        LoginRecord jack = new LoginRecord("Jack", "Sparrow", 20L);

        assertEquals(login.hashCode(), will.hashCode());
        assertNotEquals(login.hashCode(), jack.hashCode());
    }
}
