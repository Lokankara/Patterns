package patterns.example.dao.template;

import java.util.List;
import java.util.Optional;

public interface Template<T> {
    Optional<T> getByName(String name);

    List<T> getAll();

    T save(T t);

    List<T> saveAll(List<T> list);
}
