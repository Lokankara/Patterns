package patterns.rest.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import patterns.rest.model.entity.Customer;
import patterns.rest.repository.factory.CustomerDao;
import patterns.rest.repository.template.JdbcCustomerTemplate;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcCustomerDao
        implements CustomerDao { // TODO

    private final JdbcCustomerTemplate template;

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
