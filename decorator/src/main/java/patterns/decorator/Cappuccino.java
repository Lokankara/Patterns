package patterns.decorator;

public class Cappuccino extends Coffee{
    public Cappuccino() {
        super(30, 0, 0, 150);
    }

    @Override
    public double getCost() {
        return 3.00 + getCoffee() * 0.05 + getMilk() * 0.01;
    }
}
