package query.dml;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import query.SQLQuery;

import javax.sql.DataSource;
import java.sql.SQLException;

public class InsertQuery extends SQLQuery implements DMLQuery {
    private static Logger logger = LoggerFactory.getLogger(InsertQuery.class);

    private QueryRunner run;

    public InsertQuery(StringBuilder queryString, DataSource dataSource) {
        super(queryString);
        this.run = new QueryRunner(dataSource);
    }

    public int execute() throws SQLException {
        int id = 0;

        try {
            id = run.insert(this.toString(), new ScalarHandler<Integer>());

        } catch (SQLException e) {
            logger.info("Executing of query " + this + " failed");
            logger.debug(e.getLocalizedMessage());
            throw e;
        }
        return id;
    }
}
