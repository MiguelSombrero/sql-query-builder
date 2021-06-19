package query.ddl;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import query.SQLQuery;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DDLBaseQuery extends SQLQuery<Void> {
    private static Logger logger = LoggerFactory.getLogger(DDLBaseQuery.class);

    public DDLBaseQuery(StringBuilder queryString, DataSource dataSource) {
        this(queryString,  new QueryRunner(dataSource));
    }

    public DDLBaseQuery(StringBuilder queryString, QueryRunner run) {
        super(queryString, run);
    }

    public Void execute() throws SQLException {
        try {
            run.execute(this.getQueryString(), this.getParamValues());

        } catch (SQLException e) {
            logger.info("Executing of query " + this + " failed");
            logger.debug(e.getLocalizedMessage());
            throw e;
        }
        return null;
    }
}
