package minishop.persistence.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T>
{
    List<T> getAll();

    void add(T object);

    Optional<T> findById(String id);

    void remove(String id);

    void update(T updatedObject);
}
