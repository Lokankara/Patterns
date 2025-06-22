package patterns.proxy.customer;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("forwardingCustomerService")
public class ForwardingCustomerService implements CustomerService {

    private final CustomerService delegate;

    public ForwardingCustomerService(@Qualifier("interfaceCustomerService") CustomerService delegate) {
        this.delegate = delegate;
    }

    @Override
    public void create() {
        delegate.create();
    }

    @Override
    public Customer registerCustomer(String name) {
        delegate.registerCustomer(name);
        return null;
    }
}
