package query;

import java.sql.SQLException;

public interface Query<T> {
    T execute() throws SQLException;
}
