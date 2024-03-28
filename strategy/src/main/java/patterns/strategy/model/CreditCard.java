package patterns.strategy.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class CreditCard {

    private BigDecimal amount;
    private final String cvv;
    private final String name;
    private final String cardNumber;
    private final String dateOfExpiry;
}
