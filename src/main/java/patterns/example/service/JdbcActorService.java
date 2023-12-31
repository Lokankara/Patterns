package patterns.example.service;

import patterns.example.dao.ActorDao;
import patterns.example.dao.jdbc.JdbcActorDao;
import patterns.example.model.entity.Actor;

import java.util.List;
import java.util.UUID;

public class JdbcActorService {
    private final ActorDao actorDao;

    public JdbcActorService() {
        actorDao = new JdbcActorDao();
    }

    public List<Actor> saveAll(List<Actor> actors) {
        actors.forEach(genre -> genre.setActorId(
                UUID.randomUUID().getMostSignificantBits()));
        return actorDao.saveAll(actors);
    }
}
