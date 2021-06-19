package query;

import database.column.ColumnValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SQLClause extends SQLStatement implements Clause {
    protected static Logger logger = LoggerFactory.getLogger(SQLClause.class);

    private List<ColumnValue> params;

    public SQLClause(StringBuilder queryString) {
        super(queryString);
        this.params = new ArrayList<>();
    }

    public void addParam(ColumnValue param) {
        this.params.add(param);
    }

    public List<ColumnValue> getParams() {
        return this.params;
    }

    public void mergeClause(Clause subQuery) {
        append(subQuery.getQueryString());
        this.params.addAll(subQuery.getParams());
    }

    @Override
    public String toString() {
        return StringUtils.replaceQuestionMarksWithParams(this.getQueryString(), this.params);
    }

    protected Object[] getParamValues() {
        return this.params.stream()
                .map(columnValue -> columnValue.getValue())
                .toArray();
    }
}
