package patterns.dao.jdbc;

import patterns.dao.ReviewDao;
import patterns.dao.template.JdbcReviewTemplate;
import patterns.model.entity.Review;

import java.util.List;
import java.util.Optional;

public class JdbcReviewDao
        implements ReviewDao {
    private final JdbcReviewTemplate template;

    public JdbcReviewDao() {
        template = new JdbcReviewTemplate();
    }

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
