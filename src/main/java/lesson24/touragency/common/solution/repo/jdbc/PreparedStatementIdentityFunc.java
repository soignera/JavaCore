package lesson24.touragency.common.solution.repo.jdbc;

import java.sql.PreparedStatement;

@FunctionalInterface
public interface PreparedStatementIdentityFunc {

    PreparedStatement applyParamsAndGet(PreparedStatement ps) throws Exception;
}