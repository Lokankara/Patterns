package patterns.dao.jdbc;

import patterns.dao.CustomerDao;
import patterns.dao.template.JdbcCustomerTemplate;
import patterns.model.entity.Customer;

import java.util.List;
import java.util.Optional;

public class JdbcCustomerDao
        implements CustomerDao { // TODO

    private final JdbcCustomerTemplate template;

    public JdbcCustomerDao() {
        template = new JdbcCustomerTemplate();
    }

    @Override
    public Customer save(Customer customer) {
        return template.save(customer);
    }

    @Override
    public List<Customer> saveAll(List<Customer> customers) {
        return template.saveAll(customers);
    }

    @Override
    public Optional<Customer> findBy(String name) {
        return template.getByName(name);
    }

    @Override
    public List<Customer> findAll() {
        return template.getAll();
    }
}
