package lesson11.touragency.common.solution;

import java.util.List;

public interface BaseService<TYPE,ID> {

    void add(TYPE entity);

    void update(TYPE entity);

    TYPE findById(ID id);

    void deleteById(ID id);

    void delete(TYPE entity);
    void printAll();
    List<TYPE> findAll();
}
