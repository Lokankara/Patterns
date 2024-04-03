package patterns.dto.model.entity;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import patterns.dto.model.dto.LoginArgumentsProvider;
import patterns.dto.model.dto.LoginRecord;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerTest {

    @ParameterizedTest
    @ArgumentsSource(LoginArgumentsProvider.class)
    void testEqualsAndHashCodeUser(String url, String firstName, String lastName, Long id) {
        Customer customer1 = new Customer(id.intValue(), firstName, lastName);
        Customer customer2 = new Customer(id.intValue(), firstName, lastName);
        assertTrue(customer1.equals(customer2) && customer2.equals(customer1));
        assertEquals(customer1.hashCode(), customer2.hashCode());

        customer2.addShip(new Ship("Black Pearl"));
        assertFalse(customer1.equals(customer2) || customer2.equals(customer1));
        assertNotEquals(customer1.hashCode(), customer2.hashCode());
    }

    @ParameterizedTest
    @ArgumentsSource(LoginArgumentsProvider.class)
    void testEqualsAndHashCode(String url, String firstName, String lastName, Long id) {
        Customer customer1 = new Customer(id.intValue(), firstName, lastName);
        LoginRecord dto1 = new LoginRecord(firstName, lastName, id);
        Customer customer2 = new Customer(id.intValue(), firstName, lastName);
        LoginRecord dto2 = new LoginRecord(firstName, lastName, id);

        assertTrue(customer1.equals(customer2) && customer2.equals(customer1));
        assertTrue(dto1.equals(dto2) && dto2.equals(dto1));
    }

}
