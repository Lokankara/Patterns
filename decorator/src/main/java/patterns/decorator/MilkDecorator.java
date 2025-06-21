package patterns.decorator;

public class MilkDecorator extends CoffeeDecorator {
    private final double milk = 150.0;
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " milk: " + milk;
    }

    @Override
    public double getCost() {
        return super.getCost() + milk * 0.01;
    }
}
