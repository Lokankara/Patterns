package patterns.rest.repository.dao.factory;

import org.springframework.stereotype.Repository;
import patterns.rest.model.entity.Review;

@Repository
public interface ReviewDao
        extends Dao<Review> {
}
