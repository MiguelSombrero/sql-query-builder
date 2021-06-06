package query.dql;

import java.sql.SQLException;
import java.util.List;

public interface DQLQuery {
    List<Row> execute() throws SQLException;
}
