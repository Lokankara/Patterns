package patterns.rest.repository.dao.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import patterns.rest.model.entity.Actor;
import patterns.rest.repository.dao.factory.ActorDao;
import patterns.rest.repository.dao.template.JdbcActorTemplate;

import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class JdbcActorDao
        implements ActorDao {

    private final JdbcActorTemplate template;

    @Override
    public Actor save(Actor actor) {
        return template.save(actor);
    }

    @Override
    public List<Actor> saveAll(List<Actor> actors) {
        return template.saveAll(actors);
    }

    @Override
    public Optional<Actor> findBy(String name) {
        return template.getByName(name);
    }

    @Override
    public List<Actor> findAll() {
        return template.getAll();
    }
}
