package query;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SQLQuery<T> extends SQLClause implements Query<T> {
    protected static Logger logger = LoggerFactory.getLogger(SQLQuery.class);

    protected QueryRunner run;

    public SQLQuery(StringBuilder queryString, QueryRunner run) {
        super(queryString);
        this.run = run;
    }

    protected Object[] getParamValues() {
        return this.getParams().stream()
                .map(columnValue -> columnValue.getValue())
                .toArray();
    }
}
