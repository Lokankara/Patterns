package patterns.rest.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import patterns.rest.model.entity.Review;
import patterns.rest.repository.factory.ReviewDao;
import patterns.rest.repository.template.JdbcReviewTemplate;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcReviewDao
        implements ReviewDao {

    private final JdbcReviewTemplate template;

    @Override
    public Review save(Review review) {
        return template.save(review);
    }

    @Override
    public List<Review> saveAll(List<Review> reviews) {
        return template.saveAll(reviews);
    }

    @Override
    public Optional<Review> findBy(String name) {
        return template.getByName(name);
    }

    @Override
    public List<Review> findAll() {
        return template.getAll();
    }
}
