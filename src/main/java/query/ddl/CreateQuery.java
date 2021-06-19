package query.ddl;

import org.apache.commons.dbutils.QueryRunner;
import query.SQLQuery;

import javax.sql.DataSource;
import java.sql.SQLException;

public class CreateQuery extends SQLQuery<Void> {
    public CreateQuery(StringBuilder queryString, DataSource dataSource) {
        super(queryString, new QueryRunner(dataSource));
    }

    @Override
    protected Void run() throws SQLException {
        run.execute(this.getQueryString(), this.getParamValues());
        return null;
    }
}
