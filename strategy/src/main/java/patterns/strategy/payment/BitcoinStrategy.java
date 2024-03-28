package patterns.strategy.payment;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import patterns.strategy.discount.Discounter;
import patterns.strategy.model.Bitcoin;

import java.math.BigDecimal;

@Slf4j
@AllArgsConstructor
public class BitcoinStrategy
        implements PaymentStrategy {

    private Bitcoin bitcoin;
    private final Discounter discounter;
    private final PaymentDetailsCollector<Bitcoin> collector;

    @Override
    public boolean pay(BigDecimal amount) {
        BigDecimal discount = discounter.applyDiscount(amount);
        log.info("{} {}", this, discount);
        return discount.compareTo(BigDecimal.ZERO) > 0;
    }

    @Override
    public void collectPaymentDetails() {
        collector.collectPaymentDetails(Bitcoin.class, "bitcoin.json");
        this.bitcoin = collector.getDetails();
        log.info("{}", bitcoin);
    }
}
