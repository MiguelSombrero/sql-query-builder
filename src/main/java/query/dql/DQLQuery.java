package query.dql;

import database.row.Row;

import java.sql.SQLException;
import java.util.List;

public interface DQLQuery {
    List<Row> execute() throws SQLException;
}
