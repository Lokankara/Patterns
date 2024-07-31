package patterns.decorator;

public abstract class CoffeeDecorator extends Coffee {
    private final Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        super(coffee.getCoffee(), coffee.getSugar(), coffee.getWater(), coffee.getMilk());
        this.coffee = coffee;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription();
    }

    @Override
    public double getCost() {
        return coffee.getCost();
    }
}
