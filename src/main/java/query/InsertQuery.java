package query;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.sql.DataSource;
import java.sql.SQLException;

public class InsertQuery extends SQLQuery<Integer> {

    public InsertQuery(Statement statement, DataSource dataSource) {
        super(statement, new QueryRunner(dataSource));
    }

    /**
     * Executes INSERT query for this query string.
     *
     * @return Database primary key of inserted row
     *
     * @throws SQLException if SQL exception occurs
     */
    @Override
    protected Integer run() throws SQLException {
        return run.insert(this.statement.getQueryString(), new ScalarHandler<Integer>(), this.getParamValues());
    }

}
