package query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SQLQuery implements Query {
    protected static Logger logger = LoggerFactory.getLogger(SQLQuery.class);

    private StringBuilder queryString;
    private List<Object> params;

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

    public void addParam(Object param) {
        this.params.add(param);
    }

    protected Object[] getParams() {
        return this.params.toArray();
    }

    protected String getQueryString() {
        return this.queryString.toString();
    }

    public void mergeSubQuery(SQLQuery subQuery) {
        append(subQuery.getQueryString());
        this.params.addAll(Arrays.asList(subQuery.getParams()));
    }

    @Override
    public String toString() {
        return StringUtils.replaceQuestionMarksWithParams(this.queryString.toString(), this.params);
    }
}
