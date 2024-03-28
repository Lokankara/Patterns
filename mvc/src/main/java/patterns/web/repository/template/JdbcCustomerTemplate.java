package patterns.web.repository.template;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import patterns.web.model.entity.Customer;
import patterns.web.service.mapper.result.CustomerResultSetMapper;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JdbcCustomerTemplate
        implements Template<Customer> { //TODO

    private final CustomerResultSetMapper resultSetMapper;

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
