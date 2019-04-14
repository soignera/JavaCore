package lesson24.touragency.common.solution.repo.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface ResultSetExtractor<EXTRACT_TO> {
    EXTRACT_TO extract(ResultSet rs) throws SQLException;
}
