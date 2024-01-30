package patterns.rest.repository.factory;

import org.springframework.stereotype.Repository;
import patterns.rest.model.entity.Actor;

@Repository
public interface ActorDao
        extends Dao<Actor> {
}
