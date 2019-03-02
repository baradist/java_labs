package dao;

import java.util.List;
import java.util.Optional;

public interface Dao<ID, T> {
    ID create(T t);

    Optional<T> getById(ID id);

    void update(T t);

    void delete(ID id);

    List<T> getList();
}
