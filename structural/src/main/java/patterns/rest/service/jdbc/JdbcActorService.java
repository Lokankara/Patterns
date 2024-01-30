package patterns.rest.service.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patterns.rest.model.entity.Actor;
import patterns.rest.repository.factory.ActorDao;
import patterns.rest.service.ActorService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JdbcActorService implements ActorService {

    private final ActorDao actorDao;

    @Override
    public List<Actor> saveAll(List<Actor> actors) {
        actors.forEach(genre -> genre.setActorId(
                UUID.randomUUID().getMostSignificantBits()));
        return actorDao.saveAll(actors);
    }
}
