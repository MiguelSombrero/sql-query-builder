package query.dql;

import database.row.Row;
import database.row.RowHandler;
import org.apache.commons.dbutils.QueryRunner;
import query.Query;
import clause.SQLClause;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class SelectQuery extends SQLClause implements Query<List<Row>> {
    private RowHandler handler;
    private QueryRunner run;

    public SelectQuery(StringBuilder queryString, DataSource dataSource) {
        super(queryString);
        this.run = new QueryRunner(dataSource);
        this.handler = new RowHandler();
    }

    public List<Row> execute() throws SQLException {
        List<Row> result;

        try {
            result = run.query(this.getQueryString(), handler, getParamValues());

        } catch (SQLException e) {
            logger.info("Executing of query " + this + " failed");
            logger.debug(e.getLocalizedMessage());
            throw e;
        }
        return result;
    }
}
