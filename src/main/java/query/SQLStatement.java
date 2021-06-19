package query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SQLStatement implements Statement {
    protected static Logger logger = LoggerFactory.getLogger(SQLStatement.class);

    private StringBuilder queryString;

    public SQLStatement(StringBuilder queryString) {
        this.queryString = queryString;
    }

    public void append(String value) {
        this.queryString = this.queryString.append(value);
    }

    public void appendFront(String value) {
        this.queryString = this.queryString.insert(0, value);
    }

    public String getQueryString() {
        return this.queryString.toString();
    }
}
