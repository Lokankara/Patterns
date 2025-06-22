package patterns.proxy.jdbc;

import java.util.List;
import java.util.Optional;

public interface Template<T> {

    Optional<T> getByName(String name);

    List<T> getAll();

    T save(T entity);

    List<T> saveAll(List<T> entities);
}
