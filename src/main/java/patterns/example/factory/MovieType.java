package patterns.example.factory;

public interface MovieType {
    double getAmount(int daysRented);

    double getBonus(int daysRented);
}
