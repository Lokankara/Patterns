package patterns.model.entity;

import lombok.Builder;

import java.util.List;
import java.util.Objects;

@Builder
@SuppressWarnings("StringConcatenationInLoop")
public record Customer(Long customerId, String name, List<Rental> rentals) {
    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + name() + "\n";
        for (Rental each : rentals) {
            double thisAmount = 0;
            thisAmount += each.getAmount();
            frequentRenterPoints += each.getBonus();

            //determine amounts for each line
//            switch (each.movie().priceCode()) {
//
//                case REGULAR -> {
//                    thisAmount += 2;
//                    if (each.daysRented() > 2)
//                        thisAmount += (each.daysRented() - 2) * 1.5;
//                }
//                case NEW_RELEASE -> thisAmount += each.daysRented() * 3;
//                case CHILDREN -> {
//                    thisAmount += 1.5;
//                    if (each.daysRented() > 3)
//                        thisAmount += (each.daysRented() - 3) * 1.5;
//                }
//            }
//            // add frequent renter points
//            frequentRenterPoints++;
//            // add bonus for a two day new release rental
//            if ((each.movie()
//                    .priceCode() == NEW_RELEASE) && each.daysRented() > 1)
//                frequentRenterPoints++;
//            //show figures for this rental
            result += "\t" + each.movie().getTitle() + "\t" + thisAmount + "\n";
            totalAmount += thisAmount;
        }
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Customer) obj;
        return Objects.equals(this.name,
                              that.name) && Objects.equals(this.rentals,
                                                           that.rentals);
    }

    @Override
    public String toString() {
        return "Customer[" + "name=" + name + ", " + "rentals=" + rentals + ']';
    }
}
