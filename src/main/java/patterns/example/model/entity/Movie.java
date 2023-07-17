package patterns.example.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import patterns.example.factory.Type;

import java.util.List;
import java.util.Objects;

@Data
@Builder
@RequiredArgsConstructor
public final class Movie {
    private final Long movieId;
    private final String title;
    private final Review review;
    private final Type priceCode;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private final List<Genre> genres;

    public Type getPriceCode() {
        return priceCode;
    }

    public double getAmount(int daysRented) {
        return getPriceCode().getMovieType().getAmount(daysRented);
    }

    public double getBonus(int daysRented) {
        return getPriceCode().getMovieType().getBonus(daysRented);
    }

    public void addGenre(Genre genre) {
        if (!genres.contains(genre)) {
            genres.add(genre);
            genre.addMovie(this);
        }
    }

    public void removeGenre(Genre genre) {
        genres.remove(genre);
        genre.removeMovie(this);
    }

    public String getGenre() {
        return String.join(", ", genres.stream().map(Genre::getName).toList());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Movie) obj;
        return Objects.equals(this.title,
                that.title) && Objects.equals(this.priceCode,
                that.priceCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, priceCode);
    }

    @Override
    public String toString() {
        return "Movie[" + "title=" + title + ", " + "priceCode=" + priceCode + ']';
    }
}
