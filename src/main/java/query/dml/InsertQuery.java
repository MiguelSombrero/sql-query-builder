package query.dml;

import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.sql.DataSource;
import java.sql.SQLException;

public class InsertQuery extends DMLBaseQuery {

    public InsertQuery(StringBuilder queryString, DataSource dataSource) {
        super(queryString, dataSource);
    }

    /**
     * Executes INSERT database query for this query string.
     *
     * @return Database primary key of inserted row
     *
     * @throws SQLException if SQL exception occurs
     */
    @Override
    protected int run() throws SQLException {
        return run.insert(this.getQueryString(), new ScalarHandler<Integer>(), getParams());
    }

}
