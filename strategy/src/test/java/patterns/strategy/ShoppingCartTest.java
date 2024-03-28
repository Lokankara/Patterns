package patterns.strategy;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import patterns.strategy.discount.ChristmasDiscounter;
import patterns.strategy.discount.Discounter;
import patterns.strategy.discount.EasterDiscounter;
import patterns.strategy.discount.ShoppingCart;
import patterns.strategy.model.Bitcoin;
import patterns.strategy.model.CreditCard;
import patterns.strategy.model.Item;
import patterns.strategy.model.PayPal;
import patterns.strategy.payment.BitcoinStrategy;
import patterns.strategy.payment.CreditCardStrategy;
import patterns.strategy.payment.PayPalStrategy;
import patterns.strategy.payment.PaymentDetailsCollector;
import patterns.strategy.payment.PaymentStrategy;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ShoppingCartTest {

    private Discounter discounter;
    private PaymentStrategy strategy;
    private ShoppingCart shoppingCart;
    private final PaymentDetailsCollector<PayPal> paypalCollector = new PaymentDetailsCollector<>();
    private final PaymentDetailsCollector<Bitcoin> bitcoinCollector = new PaymentDetailsCollector<>();
    private final PaymentDetailsCollector<CreditCard> creditCardCollector = new PaymentDetailsCollector<>();

    @BeforeEach
    void setUp() {
        discounter = Mockito.mock(Discounter.class);
        strategy = Mockito.mock(PaymentStrategy.class);
        shoppingCart = new ShoppingCart(new ArrayList<>(), discounter, strategy);
    }

    @Test
    void addItemShouldIncreaseItemCount() {
        Item item = new Item("Movie", new BigDecimal("20.0"));
        shoppingCart.addItem(item);
        assertEquals(1, shoppingCart.getItems().size());
    }

    @Test
    void removeItemShouldDecreaseItemCount() {
        Item item = new Item("Movie", new BigDecimal("20.0"));
        shoppingCart.addItem(item);
        shoppingCart.removeItem(item);
        assertEquals(0, shoppingCart.getItems().size());
    }

    @Test
    void calculateTotalShouldReturnSumOfItemPrices() {
        Item item1 = new Item("Book", new BigDecimal("20.0"));
        Item item2 = new Item("Movie", new BigDecimal("30.0"));
        shoppingCart.addItem(item1);
        shoppingCart.addItem(item2);
        BigDecimal total = shoppingCart.calculateTotal();
        assertEquals(new BigDecimal("50.0"), total);
    }

    @SneakyThrows
    @Test
    void checkoutShouldInvokePaymentPayMethods() {
        Item item = new Item("Book", new BigDecimal("20.0"));
        shoppingCart.addItem(item);
        shoppingCart.pay(strategy);
        doNothing().when(strategy).collectPaymentDetails();
        shoppingCart.checkout();
        verify(strategy, times(1)).collectPaymentDetails();
    }

    @Test
    void payShouldReturnTrueOnPositiveDiscount() {
        PayPal payPal = new PayPal("bob@i.ua", "password");
        when(discounter.applyDiscount(Mockito.any(BigDecimal.class))).thenReturn(new BigDecimal("5.0"));
        PaymentStrategy paymentStrategy = new PayPalStrategy(payPal, discounter, paypalCollector);
        boolean result = paymentStrategy.pay(new BigDecimal("100.0"));
        assertTrue(result);
    }

    @Test
    void payShouldReturnFalseOnZeroDiscount() {
        PayPal payPal = new PayPal("bob@i.ua", "password");
        when(discounter.applyDiscount(Mockito.any(BigDecimal.class))).thenReturn(BigDecimal.ZERO);
        PaymentStrategy paymentStrategy = new PayPalStrategy(payPal, discounter, paypalCollector);
        boolean result = paymentStrategy.pay(new BigDecimal("100.0"));
        assertFalse(result);
    }

    @Test
    void payShouldReturnFalseOnNegativeDiscount() {
        PayPal payPal = new PayPal("bob@i.ua", "password");
        when(discounter.applyDiscount(Mockito.any(BigDecimal.class))).thenReturn(new BigDecimal("-5.0"));
        PaymentStrategy paymentStrategy = new PayPalStrategy(payPal, discounter, paypalCollector);
        boolean result = paymentStrategy.pay(new BigDecimal("100.0"));
        assertFalse(result);
    }

    @Test
    void payShouldSetPaymentStrategyAndInvokeCheckout() {
        Item item = new Item("Book", new BigDecimal("20.0"));
        shoppingCart.addItem(item);
        shoppingCart.pay(strategy);
        assertEquals(strategy, shoppingCart.getPayment());
    }

    @Test
    void testBitcoinStrategy() {
        BigDecimal amount = new BigDecimal(100);
        Bitcoin bitcoin = new Bitcoin("address", amount);
        BitcoinStrategy strategy = spy(new BitcoinStrategy(bitcoin, new ChristmasDiscounter(), bitcoinCollector));
        strategy.pay(amount);
        verify(strategy, times(1)).pay(amount);
    }
    @Test
    void testCreditCardPaymentStrategy() {
        BigDecimal amount = new BigDecimal(100);
        CreditCard card = new CreditCard(amount, "123", "Bob", "1234-5678-9012-3456", "12/23");
        CreditCardStrategy strategy = spy(new CreditCardStrategy(card, new EasterDiscounter(), creditCardCollector));
        strategy.pay(amount);
        verify(strategy, times(1)).pay(amount);
    }

    @Test
    void testPayPalPaymentPaymentStrategy() {
        BigDecimal amount = new BigDecimal(100);
        PayPal payPal = new PayPal("bob@i.ua", "password");
        PayPalStrategy strategy = spy(new PayPalStrategy(payPal, new EasterDiscounter(), paypalCollector));
        strategy.pay(amount);
        verify(strategy, times(1)).pay(amount);
    }
}
