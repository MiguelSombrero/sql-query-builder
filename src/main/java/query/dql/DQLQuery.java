package query.dql;

import database.Row;
import query.Query;

import java.sql.SQLException;
import java.util.List;

public interface DQLQuery extends Query {
    List<Row> execute() throws SQLException;
}
