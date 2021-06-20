package query;

import java.sql.SQLException;

public interface Query<T> extends Clause {
    T execute() throws SQLException;
}
