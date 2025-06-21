package patterns.decorator;

public class SugarDecorator extends CoffeeDecorator {
    private final double sugar = 5.0;
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", sugar: " + sugar;
    }

    @Override
    public double getCost() {
        return super.getCost() + sugar * 0.02;
    }
}
