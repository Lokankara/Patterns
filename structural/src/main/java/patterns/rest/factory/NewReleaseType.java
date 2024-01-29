package patterns.rest.factory;

public class NewReleaseType
        implements MovieType {
    @Override
    public double getAmount(int daysRented) {
        return daysRented * 3;
    }

    @Override
    public double getBonus(int daysRented) {
        return daysRented > 1 ? 2 : 1;
    }
}
