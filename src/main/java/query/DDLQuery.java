package query;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DDLQuery extends QueryTemplate {
    private static Logger logger = LoggerFactory.getLogger(DDLQuery.class);

    private QueryRunner run;

    public DDLQuery(StringBuilder queryString, DataSource dataSource) {
        super(queryString);
        this.run = new QueryRunner(dataSource);
    }

    public int execute() throws SQLException {
        int inserts = 0;

        try {
            inserts = run.update(this.toString());

        } catch (SQLException e) {
            logger.info("Executing of query " + this + " failed");
            logger.debug(e.getLocalizedMessage());
            throw e;
        }
        return inserts;
    }

    @Override
    public String toString() {
        return this.queryString.toString();
    }
}
