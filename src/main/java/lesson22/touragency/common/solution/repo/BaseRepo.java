package lesson22.touragency.common.solution.repo;

import java.util.Collection;
import java.util.List;

public interface BaseRepo<TYPE, ID> {
    TYPE add(TYPE entity);

    void add(Collection<TYPE> items);

    void update(TYPE entity);

    TYPE findById(ID id);

    void deleteById(ID id);

    void printAll();
    List<TYPE> findAll();
    int countAll();
}
