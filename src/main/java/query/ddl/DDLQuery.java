package query.ddl;

import query.Query;

import java.sql.SQLException;

public interface DDLQuery extends Query {
    void execute() throws SQLException;
}
