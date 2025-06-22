package patterns.proxy.cglib;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import patterns.proxy.customer.Customer;
import patterns.proxy.customer.CustomerService;

@Service
public class CglibCustomerService implements CustomerService {


    private final CustomerService customerService;

    public CglibCustomerService(@Qualifier("interfaceCustomerService") CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void create() {
        customerService.create();
    }

    @Override
    public Customer registerCustomer(String name) {
        customerService.registerCustomer(name);
        return null;
    }
}
