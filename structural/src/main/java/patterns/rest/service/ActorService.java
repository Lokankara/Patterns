package patterns.rest.service;

import patterns.rest.model.entity.Actor;

import java.util.List;

public interface ActorService {
    List<Actor> saveAll(List<Actor> actors);
}
