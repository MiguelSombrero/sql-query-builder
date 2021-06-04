package query.ddl;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import query.SQLQuery;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DDLBaseQuery extends SQLQuery implements DDLQuery {
    private static Logger logger = LoggerFactory.getLogger(DDLBaseQuery.class);

    private QueryRunner run;

    public DDLBaseQuery(StringBuilder queryString, DataSource dataSource) {
        super(queryString);
        this.run = new QueryRunner(dataSource);
    }

    public void execute() throws SQLException {
        try {
            run.execute(this.toString());

        } catch (SQLException e) {
            logger.info("Executing of query " + this + " failed");
            logger.debug(e.getLocalizedMessage());
            throw e;
        }
    }
}