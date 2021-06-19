package query.dql;

import database.row.Row;
import database.row.RowHandler;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.AbstractListHandler;
import query.SQLQuery;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class SelectQuery extends SQLQuery<List<Row>> {
    private AbstractListHandler<Row> handler;

    public SelectQuery(StringBuilder queryString, DataSource dataSource) {
        this(queryString, new QueryRunner(dataSource), new RowHandler());
    }

    public SelectQuery(StringBuilder queryString, QueryRunner run, AbstractListHandler<Row> handler) {
        super(queryString, run);
        this.handler = handler;
    }

    public List<Row> execute() throws SQLException {
        List<Row> result;

        try {
            result = run.query(this.getQueryString(), handler, this.getParamValues());

        } catch (SQLException e) {
            logger.info("Executing of query " + this + " failed");
            logger.debug(e.getLocalizedMessage());
            throw e;
        }
        return result;
    }
}
