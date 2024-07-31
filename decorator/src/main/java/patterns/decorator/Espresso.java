package patterns.decorator;

public class Espresso extends Coffee {
    public Espresso() {
        super(30, 0, 0, 0);
    }

    @Override
    public double getCost() {
        return 2.00 + getCoffee() * 0.05;
    }
}
