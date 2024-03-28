package patterns.web.repository.factory;

import org.springframework.stereotype.Repository;
import patterns.web.model.entity.Actor;

@Repository
public interface ActorDao
        extends Dao<Actor> {
}
