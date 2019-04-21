package lesson24.touragency.common.solution;

import lesson24.touragency.common.business.exception.UncheckedException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BaseService<TYPE,ID> {
    TYPE add(TYPE entity);
    void add(Collection<TYPE> items);

    void update(TYPE entity);

    Optional<TYPE> findById(ID id);

    void deleteById(ID id) throws UncheckedException;

    void delete(TYPE entity);
    void printAll();
    List<TYPE> findAll();
    int countAll();
}
