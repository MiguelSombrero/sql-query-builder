package query;

import database.column.AbstractColumnValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SQLQuery implements Query {
    protected static Logger logger = LoggerFactory.getLogger(SQLQuery.class);

    private StringBuilder queryString;
    private List<AbstractColumnValue> params;

    public SQLQuery(StringBuilder queryString) {
        this.queryString = queryString;
        this.params = new ArrayList<>();
    }

    public void append(String value) {
        this.queryString = this.queryString.append(value);
    }

    public void append(int value) {
        this.queryString = this.queryString.append(value);
    }

    public void append(double value) {
        this.queryString = this.queryString.append(value);
    }

    public void insert(int index, String value) {
        this.queryString = this.queryString.insert(index, value);
    }

    public void addParam(AbstractColumnValue param) {
        this.params.add(param);
    }

    protected Object[] getParams() {
        return this.params.stream()
                .map(columnValue -> columnValue.getValue())
                .toArray();
    }

    protected List<AbstractColumnValue> getParamsList() {
        return this.params;
    }

    protected String getQueryString() {
        return this.queryString.toString();
    }

    public void mergeSubQuery(SQLQuery subQuery) {
        append(subQuery.getQueryString());
        this.params.addAll(subQuery.getParamsList());
    }

    @Override
    public String toString() {
        return StringUtils.replaceQuestionMarksWithParams(this.queryString.toString(), this.params);
    }
}
