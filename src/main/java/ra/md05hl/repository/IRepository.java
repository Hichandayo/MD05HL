package ra.md05hl.repository;

import java.util.List;

public interface IRepository<T,E> {
    List<T> findAll();
    T findById(Long id);
    void save(T t);
    void deleteById(E id);
}

