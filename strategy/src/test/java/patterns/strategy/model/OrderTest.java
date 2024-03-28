package patterns.strategy.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import patterns.strategy.discount.ChristmasDiscounter;
import patterns.strategy.discount.Discounter;
import patterns.strategy.payment.PayPalStrategy;
import patterns.strategy.payment.PaymentDetailsCollector;
import patterns.strategy.payment.PaymentStrategy;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderTest {
    private Order order;
    private PaymentStrategy strategy;

    @BeforeEach
    void setUp() {
        strategy = Mockito.mock(PaymentStrategy.class);
        order = new Order(false, BigDecimal.TEN);
    }

    @Test
    void processOrderShouldCallCollectPaymentDetails() {
        PaymentDetailsCollector<PayPal> paypalCollector = new PaymentDetailsCollector<>();
        PayPal payPal = new PayPal("bob@i.ua", "password");
        Discounter discounter = new ChristmasDiscounter();
        strategy = new PayPalStrategy(payPal, discounter, paypalCollector);
        order.processOrder(strategy);
        PayPal details = paypalCollector.getDetails();
        assertNotNull(details);
    }

    @Test
    void processOrderShouldCloseOrderOnSuccessfulPayment() {
        when(strategy.pay(any(BigDecimal.class))).thenReturn(true);
        order.processOrder(strategy);
        assertTrue(order.isClosed());
    }

    @Test
    void processOrderShouldNotCloseOrderOnFailedPayment() {
        when(strategy.pay(any(BigDecimal.class))).thenReturn(false);
        order.processOrder(strategy);
        assertFalse(order.isClosed());
    }

    @Test
    void processOrderShouldLogSuccessOnSuccessfulPayment() {
        when(strategy.pay(any(BigDecimal.class))).thenReturn(true);
        order.processOrder(strategy);
        verify(strategy, times(1)).collectPaymentDetails();
        verify(strategy, times(1)).pay(BigDecimal.TEN);
    }

    @Test
    void processOrderShouldLogFailureOnFailedPayment() {
        when(strategy.pay(any(BigDecimal.class))).thenReturn(false);
        order.processOrder(strategy);
        verify(strategy, times(1)).collectPaymentDetails();
        verify(strategy, times(1)).pay(BigDecimal.TEN);
    }
}
