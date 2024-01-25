package patterns.refactoring.strategy.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import patterns.refactoring.strategy.discount.Discounter;
import patterns.refactoring.strategy.model.PayPal;

import java.math.BigDecimal;

@Slf4j
@Getter
@AllArgsConstructor
public class PayPalStrategy
        implements PaymentStrategy {

    private PayPal payPal;
    private final Discounter discounter;
    private final PaymentDetailsCollector<PayPal> collector;

    @Override
    public boolean pay(BigDecimal amount) {
        BigDecimal discount = discounter.applyDiscount(amount);
        log.info("{} {}", this, discount);
        return discount.compareTo(BigDecimal.ZERO) > 0;
    }

    @Override
    public void collectPaymentDetails() {
        collector.collectPaymentDetails(PayPal.class, "paypal.json");
        this.payPal = collector.getDetails();
        log.info("{}", payPal);
    }
}
