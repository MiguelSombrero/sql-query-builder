package clause;

import database.column.AbstractColumnValue;
import database.column.ColumnValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SQLClause implements Clause {
    protected static Logger logger = LoggerFactory.getLogger(SQLClause.class);

    private StringBuilder queryString;
    private List<ColumnValue> params;

    public SQLClause(StringBuilder queryString) {
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

    public List<ColumnValue> getParams() {
        return this.params;
    }

    public void mergeSubQuery(Clause subQuery) {
        append(subQuery.getQueryString());
        this.params.addAll(subQuery.getParams());
    }

    public String getQueryString() {
        return this.queryString.toString();
    }

    @Override
    public String toString() {
        return StringUtils.replaceQuestionMarksWithParams(this.queryString.toString(), this.params);
    }

    protected Object[] getParamValues() {
        return this.params.stream()
                .map(columnValue -> columnValue.getValue())
                .toArray();
    }
}
