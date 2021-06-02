package query.dql;

import database.Row;
import database.RowHandler;
import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import query.SQLQuery;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class SelectQuery extends SQLQuery implements DQLQuery {
    private static Logger logger = LoggerFactory.getLogger(SelectQuery.class);

    private QueryRunner run;

    public SelectQuery(StringBuilder queryString, DataSource dataSource) {
        super(queryString);
        this.run = new QueryRunner(dataSource);
    }

    public List<Row> execute() throws SQLException {
        RowHandler handler = new RowHandler();

        List<Row> result;

        try {
            result = run.query(this.toString(), handler);

        } catch (SQLException e) {
            logger.info("Executing of query " + this + " failed");
            logger.debug(e.getLocalizedMessage());
            throw e;
        }
        return result;
    }
}