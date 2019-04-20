package lesson24.touragency.common.solution.repo.jdbc;

import java.sql.PreparedStatement;

public interface PreparedStatementBiConsumer<T> extends JdbcBiConsumer<PreparedStatement, T> {
}
