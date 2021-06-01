package query;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class SelectQuery extends QueryTemplate {
    private static Logger logger = LoggerFactory.getLogger(SelectQuery.class);

    private QueryRunner run;

    public SelectQuery(StringBuilder queryString, DataSource dataSource) {
        super(queryString);
        this.run = new QueryRunner(dataSource);
    }

    public List<Object[]> execute() throws SQLException {
        ResultSetHandler<List<Object[]>> handler = new ArrayListHandler();

        List<Object[]> result;

        try {
            result = run.query(this.toString(), handler);

        } catch (SQLException e) {
            logger.info("Executing of query " + this + " failed");
            logger.debug(e.getLocalizedMessage());
            throw e;
        }
        return result;
    }

    @Override
    public String toString() {
        return this.queryString.toString();
    }
}
