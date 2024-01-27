package patterns.strategy.discount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import patterns.strategy.model.Item;
import patterns.strategy.payment.PaymentStrategy;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Getter
@AllArgsConstructor
public class ShoppingCart {

    private List<Item> items;
    private Discounter discounter;
    private PaymentStrategy payment;

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }

    public BigDecimal calculateTotal() {
        return items.stream()
                .map(Item::price)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @SneakyThrows
    public void checkout() {
        BigDecimal total = calculateTotal();
        BigDecimal discountedTotal = (discounter != null) ? discounter.applyDiscount(total) : total;
        payment.collectPaymentDetails();
        log.info(payment.pay(discountedTotal)
                           ? "Payment successful. Order placed."
                           : "Payment failed. Please try again.");
    }

    public void pay(PaymentStrategy strategy) {
        this.payment = strategy;
    }
}
