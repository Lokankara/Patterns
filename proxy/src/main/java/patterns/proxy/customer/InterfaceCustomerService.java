package patterns.proxy.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import patterns.proxy.jdbc.JdbcCustomerTemplate;
import patterns.proxy.transaction.ProxyTransactional;

@Slf4j
@ProxyTransactional
@Component
@RequiredArgsConstructor
public class InterfaceCustomerService implements CustomerService {

    private final JdbcCustomerTemplate customerTemplate;

    @Override
    public void create() {
        log.info("Creating default customer...");
        Customer registerCustomer = registerCustomer("customer");
        log.info("Registered customer {}", registerCustomer);
    }

    @Override
    public Customer registerCustomer(String name) {
        log.info("Registering customer with name: {}", name);
        Customer customer = Customer.builder()
                .id(System.currentTimeMillis())
                .login(name)
                .password("password123")
                .email(name + "@domain.com")
                .salt("staticSalt")
                .active(true)
                .build();
        return customerTemplate.save(customer);
    }
}
