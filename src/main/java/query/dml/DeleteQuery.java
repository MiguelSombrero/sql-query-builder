package query.dml;

import org.apache.commons.dbutils.QueryRunner;
import query.SQLQuery;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DeleteQuery extends SQLQuery<Integer> {

    public DeleteQuery(StringBuilder queryString, DataSource dataSource) {
        super(queryString, new QueryRunner(dataSource));
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
        return run.update(this.getQueryString(), this.getParamValues());
    }
}
