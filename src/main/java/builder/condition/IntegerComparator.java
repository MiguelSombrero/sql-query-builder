package builder.condition;

import builder.query.Query;
import builder.query.QueryAppender;
import builder.statement.select.SelectQueryBuilder;
import factory.ValidatorFactory;
import validation.Validator;

import java.util.Arrays;

public class IntegerComparator {

    private Query query;

    public IntegerComparator(Query query) {
        this.query = query;
    }

    public void equals(int value) {
        appendConditionWithValue(" = ", value);
    }

    public void greaterThan(int value) {
        appendConditionWithValue(" > ", value);
    }

    public void greaterThanOrEqual(int value) {
        appendConditionWithValue(" >= ", value);
    }

    public void lesserThan(int value) {
        appendConditionWithValue(" < ", value);
    }

    public void lesserThanOrEqual(int value) {
        appendConditionWithValue(" <= ", value);
    }

    public void isBetween(int lower, int higher) {
        appendConditionWithValue(" BETWEEN ", lower);
        appendConditionWithValue(" AND ", higher);
    }

    public void isIn(int ...listOfValues) {
        query.append(" IN ");
        QueryAppender.appendListOfValues(query, listOfValues);
    }

    private void appendConditionWithValue(String condition, int value) {
        query.append(condition);
        query.append(value);
    }
}
