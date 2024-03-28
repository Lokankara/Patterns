package patterns.web.model.entity;

import java.util.Objects;

public record Rental(Long rentalId, Movie movie, int daysRented) {
    public double getAmount() {
        return movie.getAmount(daysRented);
    }

    public double getBonus() {
        return movie.getBonus(daysRented);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rental rental = (Rental) o;
        return daysRented == rental.daysRented
                && Objects.equals(rentalId, rental.rentalId)
                && Objects.equals(movie, rental.movie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rentalId, movie, daysRented);
    }

    @Override
    public String toString() {
        return "Rental[movie=%s, daysRented=%d]".formatted(movie, daysRented);
    }
}
