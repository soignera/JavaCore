package lesson24.touragency.common.solution.repo.jdbc;
@FunctionalInterface
public interface JdbcConsumer<T> {

    void consume(T t) throws Exception;
}
