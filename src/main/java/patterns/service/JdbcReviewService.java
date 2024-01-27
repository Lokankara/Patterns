package patterns.service;

import patterns.dao.jdbc.JdbcReviewDao;
import patterns.dao.ReviewDao;
import patterns.model.entity.Actor;
import patterns.model.entity.Review;

import java.util.List;
import java.util.UUID;

public class JdbcReviewService {
    private final ReviewDao reviewDao;
    private final JdbcActorService actorService;

    public JdbcReviewService() {
        this.reviewDao = new JdbcReviewDao();
        this.actorService = new JdbcActorService();
    }

    public Review save(Review review) {
        review.setReviewId(UUID.randomUUID().getMostSignificantBits());
        List<Actor> actors = actorService.saveAll(review.getActors());
        Review saved = reviewDao.save(review);
        saved.setActors(actors);
        return saved;
    }
}
