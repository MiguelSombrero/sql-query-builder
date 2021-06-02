package query.dml;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import query.SQLQuery;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DeleteQuery extends SQLQuery implements DMLQuery {
    private static Logger logger = LoggerFactory.getLogger(DeleteQuery.class);

    private QueryRunner run;

    public DeleteQuery(StringBuilder queryString, DataSource dataSource) {
        super(queryString);
        this.run = new QueryRunner(dataSource);
    }

    public int execute() throws SQLException {
        int deletes = 0;

        try {
            deletes = run.update(this.toString());

        } catch (SQLException e) {
            logger.info("Executing of query " + this + " failed");
            logger.debug(e.getLocalizedMessage());
            throw e;
        }
        return deletes;
    }
}
