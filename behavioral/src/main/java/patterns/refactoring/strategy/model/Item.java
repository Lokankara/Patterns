package patterns.refactoring.strategy.model;

import java.math.BigDecimal;

public record Item(String upcCode, BigDecimal price) {
}
