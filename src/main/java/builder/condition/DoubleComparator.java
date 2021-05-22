package builder.condition;

import builder.query.Query;
import builder.query.QueryAppender;

public class DoubleComparator {

    private Query query;

    public DoubleComparator(Query query) {
        this.query = query;
    }

    public void equals(double value) {
        appendConditionWithValue(" = ", value);
    }

    public void greaterThan(double value) {
        appendConditionWithValue(" > ", value);
    }

    public void greaterThanOrEqual(double value) {
        appendConditionWithValue(" >= ", value);
    }

    public void lesserThan(double value) {
        appendConditionWithValue(" < ", value);
    }

    public void lesserThanOrEqual(double value) {
        appendConditionWithValue(" <= ", value);
    }

    public void isBetween(double lower, double higher) {
        appendConditionWithValue(" BETWEEN ", lower);
        appendConditionWithValue(" AND ", higher);
    }

    public void isIn(double ...listOfValues) {
        query.append(" IN ");
        QueryAppender.appendListOfValues(query, listOfValues);
    }

    private void appendConditionWithValue(String condition, double value) {
        query.append(condition);
        query.append(value);
    }
}
