package lesson24.touragency.common.solution;

import lesson24.touragency.common.business.exception.UncheckedException;

import java.util.Collection;
import java.util.List;

public interface BaseService<TYPE,ID> {
    TYPE add(TYPE entity);
    void add(Collection<TYPE> items);

    void update(TYPE entity);

    TYPE findById(ID id);

    void deleteById(ID id) throws UncheckedException;

    void delete(TYPE entity);
    void printAll();
    List<TYPE> findAll();
    int countAll();
}
