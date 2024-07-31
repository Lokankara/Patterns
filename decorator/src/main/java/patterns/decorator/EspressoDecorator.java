package patterns.decorator;

public class EspressoDecorator extends CoffeeDecorator {

    public EspressoDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " with coffee: " + getCoffee();
    }

    @Override
    public double getCost() {
        return super.getCost();
    }
}