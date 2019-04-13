package lesson22.touragency.common.solution.repo.jdbc;
@FunctionalInterface
public interface JdbcConsumer<T> {

    void consume(T t) throws Exception;
}
