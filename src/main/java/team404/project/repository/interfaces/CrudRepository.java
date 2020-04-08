package team404.project.repository.interfaces;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {
    void save(T var1);

    void saveAll(List<T> var1);

    Optional<T> findById(ID var1);

    boolean existsById(ID var1);

    Iterable<T> findAll();

    Iterable<T> findAllById(Iterable<ID> var1);

    long count();

    void deleteById(ID var1);

    void delete(T var1);

    void deleteAll(Iterable<? extends T> var1);

    void deleteAll();
}
