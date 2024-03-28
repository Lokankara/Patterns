package patterns.strategy.payment;

import java.math.BigDecimal;

public interface PaymentStrategy {
    boolean pay(BigDecimal amount);

    void collectPaymentDetails();
}
