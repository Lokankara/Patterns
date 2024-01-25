package patterns.refactoring.strategy.payment;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import patterns.refactoring.strategy.discount.Discounter;
import patterns.refactoring.strategy.model.CreditCard;

import java.math.BigDecimal;

@Slf4j
@AllArgsConstructor
public class CreditCardStrategy
        implements PaymentStrategy {

    private CreditCard card;
    private final Discounter discounter;
    private final PaymentDetailsCollector<CreditCard> collector;

    @Override
    public boolean pay(BigDecimal amount) {
        BigDecimal discount = discounter.applyDiscount(amount);
        log.info("{} {}", this, discount);
        return discount.compareTo(BigDecimal.ZERO) > 0;
    }

    @Override
    public void collectPaymentDetails() {
        collector.collectPaymentDetails(CreditCard.class, "credit.json");
        this.card = collector.getDetails();
        log.info("{}", card);
    }
}
