package query;

import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DeleteQuery extends SQLQuery<Integer> {

    public DeleteQuery(Statement statement, DataSource dataSource) {
        super(statement, new QueryRunner(dataSource));
    }

    /**
     * Executes DELETE query for this query string.
     *
     * @return Number of rows deleted
     *
     * @throws SQLException if SQL exception occurs
     */
    @Override
    protected Integer run() throws SQLException {
        return run.update(this.statement.getQueryString(), this.getParamValues());
    }
}
