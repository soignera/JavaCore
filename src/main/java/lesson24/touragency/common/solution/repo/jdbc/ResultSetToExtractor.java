package lesson24.touragency.common.solution.repo.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@FunctionalInterface
public interface ResultSetToExtractor<EXTRACT_TO> {
    void extract(ResultSet rs, List<EXTRACT_TO> accumulator) throws SQLException;
}