package query;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public abstract class SQLQuery<T> implements Query<T> {
    protected static Logger logger = LoggerFactory.getLogger(SQLQuery.class);

    protected Clause clause;
    protected QueryRunner run;

    public SQLQuery(Clause clause, QueryRunner run) {
        this.clause = clause;
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
        return this.clause.getParams().stream()
                .map(columnValue -> columnValue.getValue())
                .toArray();
    }

    public Clause getClause() {
        return this.clause;
    }

    @Override
    public String toString() {
        return this.clause.toString();
    }

    protected abstract T run() throws SQLException;
}
