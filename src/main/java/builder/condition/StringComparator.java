package builder.condition;

import builder.query.Query;
import builder.query.QueryAppender;
import builder.statement.select.SelectQueryBuilder;
import factory.ValidatorFactory;
import validation.Validator;

import java.util.Arrays;

public class StringComparator {
    private static Validator validator = ValidatorFactory.exceptionThrowingStringValueValidator();

    private Query query;

    public StringComparator(Query query) {
        this.query = query;
    }

    public void equals(String value) {
        appendConditionWithValue(" = ", value);
    }

    public void greaterThan(String value) {
        appendConditionWithValue(" > ", value);
    }

    public void greaterThanOrEqual(String value) {
        appendConditionWithValue(" >= ", value);
    }

    public void lesserThan(String value) {
        appendConditionWithValue(" < ", value);
    }

    public void lesserThanOrEqual(String value) {
        appendConditionWithValue(" <= ", value);
    }

    public void startsWith(String pattern) {
        validator.validate(pattern);
        String startsWith = pattern.concat("%");
        query.append(" LIKE ");
        QueryAppender.appendStringValue(query, startsWith);
    }

    public void endsWith(String pattern) {
        validator.validate(pattern);
        String endsWith = "%".concat(pattern);
        query.append(" LIKE ");
        QueryAppender.appendStringValue(query, endsWith);
    }

    public void contains(String pattern) {
        validator.validate(pattern);
        String contains = "%".concat(pattern).concat("%");
        query.append(" LIKE ");
        QueryAppender.appendStringValue(query, contains);
    }

    public void isBetween(String lower, String higher) {
        appendConditionWithValue(" BETWEEN ", lower);
        appendConditionWithValue(" AND ", higher);
    }

    public void isIn(String ...listOfValues) {
        validateList(listOfValues);
        query.append(" IN ");
        QueryAppender.appendListOfValues(query, listOfValues);
    }

    private void appendConditionWithValue(String condition, String value) {
        validator.validate(value);
        query.append(condition);
        QueryAppender.appendStringValue(query, value);
    }

    private void validateList(String[] columns) {
        Arrays.stream(columns).forEach(column -> validator.validate(column));
    }
}
