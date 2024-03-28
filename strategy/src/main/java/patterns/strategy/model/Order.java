package patterns.strategy.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import patterns.strategy.payment.PaymentStrategy;

import java.math.BigDecimal;

@Slf4j
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Order {

    private boolean isClosed;
    private BigDecimal amount;

    public void processOrder(PaymentStrategy strategy) {
        strategy.collectPaymentDetails();
        if (strategy.pay(amount)) {
            isClosed = true;
            log.info("Order processed successfully.");
        } else {
            log.info("Payment failed. Order not processed.");
        }
    }
}
