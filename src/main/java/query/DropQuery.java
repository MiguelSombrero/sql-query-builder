package query;

import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DropQuery extends SQLQuery<Void> {
    public DropQuery(StringBuilder queryString, DataSource dataSource) {
        super(queryString, new QueryRunner(dataSource));
    }

    @Override
    protected Void run() throws SQLException {
        run.execute(this.getQueryString(), this.getParamValues());
        return null;
    }
}
