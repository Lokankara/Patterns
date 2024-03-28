package patterns.web.service;

import patterns.web.model.entity.Actor;

import java.util.List;

public interface ActorService {
    List<Actor> saveAll(List<Actor> actors);
}
