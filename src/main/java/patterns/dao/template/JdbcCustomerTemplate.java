package patterns.dao.template;

import patterns.dao.mapper.result.CustomerResultSetMapper;
import patterns.model.entity.Customer;

import java.util.List;
import java.util.Optional;

public class JdbcCustomerTemplate implements Template<Customer> { //TODO
    private final CustomerResultSetMapper resultSetMapper;

    public JdbcCustomerTemplate() {
        this.resultSetMapper = new CustomerResultSetMapper();
    }

    @Override
    public Optional<Customer> getByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Customer> getAll() {
        return null;
    }

    @Override
    public Customer save(Customer customer) {
        return null;
    }

    @Override
    public List<Customer> saveAll(List<Customer> list) {
        return null;
    }
}
