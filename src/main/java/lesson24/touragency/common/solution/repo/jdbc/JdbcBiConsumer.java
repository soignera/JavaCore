package lesson24.touragency.common.solution.repo.jdbc;


@FunctionalInterface
public interface JdbcBiConsumer<T1, T2> {
    void consume(T1 t1, T2 t2) throws Exception;
}
