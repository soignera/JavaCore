package lesson24.touragency.common.solution.repo;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BaseRepo<TYPE, ID> {
    TYPE add(TYPE entity);

    void add(Collection<TYPE> items);

    void update(TYPE entity);

    Optional<TYPE> findById(ID id);

    void deleteById(ID id);

    void printAll();
    List<TYPE> findAll();
    int countAll();
}
