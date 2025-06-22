package patterns.proxy.customer;

import patterns.proxy.transaction.ProxyTransactional;

public interface CustomerService {
    @ProxyTransactional
    void create();

    @ProxyTransactional
    Customer registerCustomer(String name);
}