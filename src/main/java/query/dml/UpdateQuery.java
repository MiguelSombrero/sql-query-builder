package query.dml;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import query.SQLQuery;

import javax.sql.DataSource;
import java.sql.SQLException;

public class UpdateQuery extends SQLQuery implements DMLQuery {
    private static Logger logger = LoggerFactory.getLogger(UpdateQuery.class);

    private QueryRunner run;

    public UpdateQuery(StringBuilder queryString, DataSource dataSource) {
        super(queryString);
        this.run = new QueryRunner(dataSource);
    }

    public int execute() throws SQLException {
        int updates = 0;

        try {
            updates = run.update(this.toString());

        } catch (SQLException e) {
            logger.info("Executing of query " + this + " failed");
            logger.debug(e.getLocalizedMessage());
            throw e;
        }
        return updates;
    }
}
