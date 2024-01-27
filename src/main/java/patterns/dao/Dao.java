package patterns.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    T save(T t);

    List<T> saveAll(List<T> list);

    List<T> findAll();

    Optional<T> findBy(String name);
}
