package patterns.decorator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class CoffeeDecoratorTest {

    @Test
    public void testEspresso() {
        Coffee espresso = new EspressoDecorator(new Espresso());
        assertEquals("Espresso with coffee: 30.0", espresso.getDescription());
        assertEquals(3.50, espresso.getCost());
    }

    @Test
    public void testCappuccinoWithDecorators() {
        Coffee cappuccino = new MilkDecorator(new SugarDecorator(new Cappuccino()));
        assertEquals("Cappuccino with coffee: 30.0, milk: 150.0 with coffee: 30.0, milk: 150.0", cappuccino.getDescription());
        assertEquals(3.00 + 150 * 0.01, cappuccino.getCost());
    }

    @Test
    public void testLatteWithDecorators() {
        Coffee latte = new SugarDecorator(new MilkDecorator(new Espresso()));
        assertEquals("Espresso with coffee: 30.0 with coffee: 30.0", latte.getDescription());
        assertEquals(2.00 + 0.20 + 0.10 + 5 * 0.02, latte.getCost());
    }

    @Test
    public void testDoubleDecorator() {
        Coffee coffee = new Cappuccino();
        coffee = new MilkDecorator(coffee);
        coffee = new SugarDecorator(coffee);
        assertEquals("Cappuccino with coffee: 30.0, milk: 150.0 with coffee: 30.0, milk: 150.0", coffee.getDescription());
        assertEquals(3.00 + 150 * 0.01 + 0.10 + 5 * 0.02, coffee.getCost());
    }
}
