package patterns.decorator;

public class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", milk: " + getMilk();
    }

    @Override
    public double getCost() {
        return super.getCost() + getMilk() * 0.01;
    }
}
