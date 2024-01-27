package patterns.factory;

public interface MovieType {
    double getAmount(int daysRented);

    double getBonus(int daysRented);
}
