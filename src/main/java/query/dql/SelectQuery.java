package query.dql;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import query.SQLQuery;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class SelectQuery extends SQLQuery implements DQLQuery {
    private static Logger logger = LoggerFactory.getLogger(SelectQuery.class);

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
            result = run.query(this.getQueryString(), handler, getParams());

        } catch (SQLException e) {
            logger.info("Executing of query " + this + " failed");
            logger.debug(e.getLocalizedMessage());
            throw e;
        }
        return result;
    }
}
