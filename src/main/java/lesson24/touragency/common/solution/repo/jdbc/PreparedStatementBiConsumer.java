package lesson24.touragency.common.solution.repo.jdbc;

import lesson22.touragency.common.solution.repo.jdbc.JdbcBiConsumer;

import java.sql.PreparedStatement;

public interface PreparedStatementBiConsumer<T> extends JdbcBiConsumer<PreparedStatement, T> {
}
