package query;

import database.column.ColumnValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SQLStatement implements Statement {
    protected static Logger logger = LoggerFactory.getLogger(SQLStatement.class);
    private static final String PARAM = "?";

    private StringBuilder queryString;
    private List<ColumnValue> params;

    public SQLStatement(StringBuilder queryString) {
        this.queryString = queryString;
        this.params = new ArrayList<>();
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

    public void addParam(ColumnValue param) {
        append(PARAM);
        this.params.add(param);
    }

    public List<ColumnValue> getParams() {
        return this.params;
    }

    public void mergeStatement(Statement subQuery) {
        append(subQuery.getQueryString());
        this.params.addAll(subQuery.getParams());
    }

    @Override
    public String toString() {
        return StringUtils.replaceQuestionMarksWithParams(this.getQueryString(), this.params);
    }
}
