package query.dml;

import query.Query;

import java.sql.SQLException;

public interface DMLQuery extends Query {
    int execute() throws SQLException;
}
