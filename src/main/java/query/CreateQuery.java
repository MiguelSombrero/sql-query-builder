package query;

import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

public class CreateQuery extends SQLQuery<Void> {
    public CreateQuery(Clause clause, DataSource dataSource) {
        super(clause, new QueryRunner(dataSource));
    }

    @Override
    protected Void run() throws SQLException {
        run.execute(this.clause.getQueryString(), this.getParamValues());
        return null;
    }
}
