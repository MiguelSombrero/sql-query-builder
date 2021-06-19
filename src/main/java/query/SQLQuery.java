package query;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public abstract class SQLQuery<T> extends SQLClause implements Query<T> {
    protected static Logger logger = LoggerFactory.getLogger(SQLQuery.class);

    protected QueryRunner run;

    public SQLQuery(StringBuilder queryString, QueryRunner run) {
        super(queryString);
        this.run = run;
    }

    public T execute() throws SQLException {
        T result;

        try {
            result = this.run();

        } catch (SQLException e) {
            logger.info("Executing of query " + this + " failed");
            logger.debug(e.getLocalizedMessage());
            throw e;
        }
        return result;
    }

    protected Object[] getParamValues() {
        return this.getParams().stream()
                .map(columnValue -> columnValue.getValue())
                .toArray();
    }

    protected abstract T run() throws SQLException;
}
