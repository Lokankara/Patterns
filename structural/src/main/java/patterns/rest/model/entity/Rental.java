package patterns.rest.model.entity;

import java.util.Objects;

public record Rental(Long rentalId, Movie movie, int daysRented) {
    public double getAmount() {
        return movie.getAmount(daysRented);
    }

    public double getBonus() {
        return movie.getBonus(daysRented);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Rental) obj;
        return Objects.equals(this.movie, that.movie) && this.daysRented == that.daysRented;
    }

    @Override
    public String toString() {
        return "Rental[movie=%s, daysRented=%d]".formatted(movie, daysRented);
    }

}
