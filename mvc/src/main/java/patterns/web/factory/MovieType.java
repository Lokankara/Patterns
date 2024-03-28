package patterns.web.factory;

public interface MovieType {
    double getAmount(int daysRented);

    double getBonus(int daysRented);
}
