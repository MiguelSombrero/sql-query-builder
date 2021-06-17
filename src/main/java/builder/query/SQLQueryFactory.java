package builder.query;

import query.ddl.CreateQuery;
import query.ddl.DropQuery;
import query.dml.DeleteQuery;
import query.dml.InsertQuery;
import query.dml.UpdateQuery;
import query.dql.SelectQuery;

import javax.sql.DataSource;

public class SQLQueryFactory {
    public DataSource dataSource;

    public SQLQueryFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public SelectQuery createSelectQuery() {
        return new SelectQuery(createQueryString("SELECT "), dataSource);
    }

    public SelectQuery createSelectDistinctQuery() {
        return new SelectQuery(createQueryString("SELECT DISTINCT "), dataSource);
    }

    public SelectQuery createSelectTopQuery(int rows) {
        return new SelectQuery(createQueryString("SELECT TOP " + rows + " "), dataSource);
    }

    public InsertQuery createInsertQuery() {
        return new InsertQuery(createQueryString("INSERT INTO "), dataSource);
    }

    public DeleteQuery createDeleteQuery() {
        return new DeleteQuery(createQueryString("DELETE FROM "), dataSource);
    }

    public UpdateQuery createUpdateQuery() {
        return new UpdateQuery(createQueryString("UPDATE "), dataSource);
    }

    public CreateQuery createCreateQuery() {
        return new CreateQuery(createQueryString("CREATE "), dataSource);
    }

    public DropQuery createDropQuery() {
        return new DropQuery(createQueryString("DROP "), dataSource);
    }

    private StringBuilder createQueryString(String queryString) {
        return new StringBuilder(queryString);
    }
}
