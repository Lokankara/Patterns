package patterns.example.service;

import patterns.example.dao.jdbc.JdbcReviewDao;
import patterns.example.dao.ReviewDao;
import patterns.example.model.entity.Actor;
import patterns.example.model.entity.Review;

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
