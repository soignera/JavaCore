package lesson22.touragency.common.solution.repo.jdbc;

import java.sql.PreparedStatement;

public interface PreparedStatementBiConsumer<T> extends JdbcBiConsumer<PreparedStatement, T>  {
}
