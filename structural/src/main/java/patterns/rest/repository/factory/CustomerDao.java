package patterns.rest.repository.factory;

import org.springframework.stereotype.Repository;
import patterns.rest.model.entity.Customer;

@Repository
public interface CustomerDao
        extends Dao<Customer> {
}
