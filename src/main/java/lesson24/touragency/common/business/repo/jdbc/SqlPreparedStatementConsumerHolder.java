package lesson24.touragency.common.business.repo.jdbc;

import lesson24.touragency.common.solution.repo.jdbc.PreparedStatementConsumer;

import java.util.List;

public class SqlPreparedStatementConsumerHolder {
    private String sql;
    private List<PreparedStatementConsumer> preparedStatementConsumers;

    public SqlPreparedStatementConsumerHolder(String sql, List<PreparedStatementConsumer> preparedStatementConsumers) {
        this.sql = sql;
        this.preparedStatementConsumers = preparedStatementConsumers;
    }

    public String getSql() {
        return sql;
    }

    public List<PreparedStatementConsumer> getPreparedStatementConsumers() {
        return preparedStatementConsumers;
    }
}
