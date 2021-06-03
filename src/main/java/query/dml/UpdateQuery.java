package query.dml;

import javax.sql.DataSource;
import java.sql.SQLException;

public class UpdateQuery extends DMLBaseQuery {

    public UpdateQuery(StringBuilder queryString, DataSource dataSource) {
        super(queryString, dataSource);
    }

    /**
     * Executes UPDATE database query for this query string.
     *
     * @return Number of rows updated
     *
     * @throws SQLException if SQL exception occurs
     */
    @Override
    protected int run() throws SQLException {
        return run.update(this.toString());
    }
}
