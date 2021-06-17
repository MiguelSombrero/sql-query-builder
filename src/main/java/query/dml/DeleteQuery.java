package query.dml;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DeleteQuery extends DMLBaseQuery {

    public DeleteQuery(StringBuilder queryString, DataSource dataSource) {
        super(queryString, dataSource);
    }

    /**
     * Executes DELETE database query for this query string.
     *
     * @return Number of rows deleted
     *
     * @throws SQLException if SQL exception occurs
     */
    @Override
    protected int run() throws SQLException {
        return run.update(this.getQueryString(), getParamValues());
    }
}
