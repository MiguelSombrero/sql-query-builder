package query.dml;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import query.Query;
import clause.SQLClause;

import javax.sql.DataSource;
import java.sql.SQLException;

public abstract class DMLBaseQuery extends SQLClause implements Query<Integer> {
    protected static Logger logger = LoggerFactory.getLogger(DMLBaseQuery.class);

    protected QueryRunner run;

    public DMLBaseQuery(StringBuilder queryString, DataSource dataSource) {
        super(queryString);
        this.run = new QueryRunner(dataSource);
    }

    /**
     * Executes INSERT, UPDATE or DELETE query.
     *
     * @return Database primary key of inserted row when INSERT query.
     * Number of rows updated or deleted when UPDATE or DELETE query.
     *
     * @throws SQLException when SQL error occurs
     */
    public Integer execute() throws SQLException {
        int result = 0;

        try {
            result = this.run();

        } catch (SQLException e) {
            logger.info("Executing of query " + this + " failed");
            logger.debug(e.getLocalizedMessage());
            throw e;
        }
        return result;
    }

    protected abstract int run() throws SQLException;
}
