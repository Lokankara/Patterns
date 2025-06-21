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
        Coffee cappuccino = new SugarDecorator(new MilkDecorator(new Cappuccino()));
        assertEquals("Cappuccino milk: 150.0, sugar: 5.0", cappuccino.getDescription());
        assertEquals(7.6, cappuccino.getCost());
    }

    @Test
    public void testLatteWithDecorators() {
        Coffee latte = new SugarDecorator(new MilkDecorator(new Espresso()));
        assertEquals("Espresso milk: 150.0, sugar: 5.0", latte.getDescription());
        assertEquals(5.1, latte.getCost());
    }

    @Test
    public void testDoubleDecorator() {
        Coffee coffee = new Cappuccino();
        coffee = new MilkDecorator(coffee);
        coffee = new SugarDecorator(coffee);
        assertEquals("Cappuccino milk: 150.0, sugar: 5.0", coffee.getDescription());
        assertEquals(7.6, coffee.getCost());
    }
}
