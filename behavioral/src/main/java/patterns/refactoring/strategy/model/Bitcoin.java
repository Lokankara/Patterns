package patterns.refactoring.strategy.model;

import java.math.BigDecimal;

public record Bitcoin(String address, BigDecimal amount) {
}
