package patterns.refactoring.dao.jdbc;

import patterns.refactoring.dao.CustomerDao;
import patterns.refactoring.dao.template.JdbcCustomerTemplate;
import patterns.refactoring.model.entity.Customer;

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
