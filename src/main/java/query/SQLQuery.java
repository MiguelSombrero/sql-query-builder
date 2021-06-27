package query;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public abstract class SQLQuery<T> implements Query<T> {
    protected static Logger logger = LoggerFactory.getLogger(SQLQuery.class);

    protected Statement statement;
    protected QueryRunner run;

    public SQLQuery(Statement statement, QueryRunner run) {
        this.statement = statement;
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
        return this.statement.getParams().stream()
                .map(columnValue -> columnValue.getValue())
                .toArray();
    }

    public Statement getStatement() {
        return this.statement;
    }

    @Override
    public String toString() {
        return this.statement.toString();
    }

    protected abstract T run() throws SQLException;
}
