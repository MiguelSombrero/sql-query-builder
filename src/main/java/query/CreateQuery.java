package query;

import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

public class CreateQuery extends SQLQuery<Void> {
    public CreateQuery(Statement statement, DataSource dataSource) {
        super(statement, new QueryRunner(dataSource));
    }

    @Override
    protected Void run() throws SQLException {
        run.execute(this.statement.getQueryString(), this.getParamValues());
        return null;
    }
}
