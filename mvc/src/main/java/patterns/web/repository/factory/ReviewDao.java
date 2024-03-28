package patterns.web.repository.factory;

import org.springframework.stereotype.Repository;
import patterns.web.model.entity.Review;

@Repository
public interface ReviewDao
        extends Dao<Review> {
}
