package patterns.rest.service.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patterns.rest.model.entity.Actor;
import patterns.rest.model.entity.Review;
import patterns.rest.repository.dao.factory.ReviewDao;
import patterns.rest.service.ReviewService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JdbcReviewService implements ReviewService {

    private final ReviewDao reviewDao;
    private final JdbcActorService actorService;

    @Override
    public Review save(Review review) {
        review.setReviewId(UUID.randomUUID().getMostSignificantBits());
        List<Actor> actors = actorService.saveAll(review.getActors());
        Review saved = reviewDao.save(review);
        saved.setActors(actors);
        return saved;
    }
}
