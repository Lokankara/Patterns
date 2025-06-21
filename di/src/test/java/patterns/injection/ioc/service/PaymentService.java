package patterns.injection.ioc.service;

import lombok.Setter;

import java.util.Objects;

@Setter
public class PaymentService {
    private MailService mailService;
    private int maxAmount;

    public PaymentService() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentService that = (PaymentService) o;
        return maxAmount == that.maxAmount &&
                Objects.equals(mailService, that.mailService);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mailService, maxAmount);
    }

}
