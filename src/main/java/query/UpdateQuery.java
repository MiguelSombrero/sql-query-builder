package query;

import org.apache.commons.dbutils.QueryRunner;
import query.SQLQuery;

import javax.sql.DataSource;
import java.sql.SQLException;

public class UpdateQuery extends SQLQuery<Integer> {

    public UpdateQuery(StringBuilder queryString, DataSource dataSource) {
        super(queryString, new QueryRunner(dataSource));
    }

    /**
     * Executes UPDATE query for this query string.
     *
     * @return Number of rows updated
     *
     * @throws SQLException if SQL exception occurs
     */
    @Override
    protected Integer run() throws SQLException {
        return run.update(this.getQueryString(), this.getParamValues());
    }
}
