package lesson10v2.touragency.common.solution;

public interface BaseService<TYPE,ID> {

    void add(TYPE entity);

    void update(TYPE entity);

    TYPE findById(ID id);

    void deleteById(ID id);

    void delete(TYPE entity);
    void printAll();
}
