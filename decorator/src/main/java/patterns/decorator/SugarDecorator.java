package patterns.decorator;

public class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", sugar: " + getSugar();
    }

    @Override
    public double getCost() {
        return super.getCost() + getSugar() * 0.02;
    }
}
