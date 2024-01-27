package patterns.dao.jdbc;

import patterns.dao.ActorDao;
import patterns.dao.template.JdbcActorTemplate;
import patterns.model.entity.Actor;

import java.util.List;
import java.util.Optional;

public class JdbcActorDao
        implements ActorDao {
    private final JdbcActorTemplate template;

    public JdbcActorDao() {
        template = new JdbcActorTemplate();
    }

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
