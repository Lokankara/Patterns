package patterns.strategy.discount;

import java.math.BigDecimal;

public class ChristmasDiscounter
        implements Discounter {
    @Override
    public BigDecimal applyDiscount(BigDecimal amount) {
        return amount.multiply(BigDecimal.valueOf(.75));
    }
}
