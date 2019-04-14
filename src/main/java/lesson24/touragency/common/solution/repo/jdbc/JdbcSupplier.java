package lesson24.touragency.common.solution.repo.jdbc;

public interface JdbcSupplier<T> {
    T get() throws Exception;
}
