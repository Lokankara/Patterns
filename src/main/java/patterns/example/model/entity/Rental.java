package patterns.example.model.entity;

import java.util.Objects;

public final class Rental {
    private final Long rentalId;
    private final Movie movie;
    private final int daysRented;

    public double getAmount() {
        return movie.getAmount(daysRented);
    }

    public double getBonus() {
        return movie.getBonus(daysRented);
    }

    public Rental(
            Long rentalId,
            Movie movie,
            int daysRented) {
        this.rentalId = rentalId;
        this.movie = movie;
        this.daysRented = daysRented;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Rental) obj;
        return Objects.equals(this.movie,
                              that.movie) && this.daysRented == that.daysRented;
    }

    @Override
    public String toString() {
        return "Rental[" + "movie=" + movie + ", " + "daysRented=" + daysRented + ']';
    }

    public Long rentalId() {
        return rentalId;
    }

    public Movie movie() {
        return movie;
    }

    public int daysRented() {
        return daysRented;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rentalId, movie, daysRented);
    }


}
