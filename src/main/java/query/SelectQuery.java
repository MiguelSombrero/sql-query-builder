package query;

import database.row.Row;
import database.row.RowHandler;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.AbstractListHandler;

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

    /**
     * Executes SELECT query for this query string. Uses
     * AbstractListHandler to create Row objects from
     * database ResultSet.
     *
     * @return List of Row objects which represents database rows
     *
     * @throws SQLException if SQL exception occurs
     */
    @Override
    protected List<Row> run() throws SQLException {
        return run.query(this.getQueryString(), handler, this.getParamValues());
    }
}
