package patterns.web.repository.factory;

import org.springframework.stereotype.Repository;
import patterns.web.model.entity.Customer;

@Repository
public interface CustomerDao extends Dao<Customer> {
}
