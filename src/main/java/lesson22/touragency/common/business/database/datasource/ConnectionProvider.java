package lesson22.touragency.common.business.database.datasource;

import java.sql.Connection;
import java.sql.SQLException;

@FunctionalInterface

public interface ConnectionProvider {
    Connection getConnection() throws SQLException;

}
