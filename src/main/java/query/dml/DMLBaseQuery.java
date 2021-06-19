package query.dml;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import query.SQLQuery;

import javax.sql.DataSource;
import java.sql.SQLException;

public abstract class DMLBaseQuery extends SQLQuery<Integer> {
    protected static Logger logger = LoggerFactory.getLogger(DMLBaseQuery.class);

    public DMLBaseQuery(StringBuilder queryString, DataSource dataSource) {
        this(queryString, new QueryRunner(dataSource));
    }

    public DMLBaseQuery(StringBuilder queryString, QueryRunner run) {
        super(queryString, run);
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
        int result;

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
