package builder.condition;

import builder.QueryBuilder;
import builder.query.Query;
import builder.query.QueryAppender;
import factory.ValidatorFactory;
import validation.Validator;

import java.util.Arrays;

public class Comparison {
    private static Validator validator = ValidatorFactory.exceptionThrowingStringValueValidator();

    protected Query query;

    public Comparison(Query query) {
        this.query = query;
    }

    public Condition equals(String value) {
        return appendConditionWithValue(" = ", value);
    }

    public Condition equals(Integer value) {
        return appendConditionWithValue(" = ", value);
    }

    public Condition equals(Double value) {
        return appendConditionWithValue(" = ", value);
    }

    public Condition equalsAny(QueryBuilder query) {
        return appendConditionWithSubQuery(" = ANY ", query);
    }

    public Condition equalsAll(QueryBuilder query) {
        return appendConditionWithSubQuery(" = ALL ", query);
    }

    public Condition greaterThan(String value) {
        return appendConditionWithValue(" > ", value);
    }

    public Condition greaterThan(Integer value) {
        return appendConditionWithValue(" > ", value);
    }

    public Condition greaterThan(Double value) {
        return appendConditionWithValue(" > ", value);
    }

    public Condition greaterThanAny(QueryBuilder query) {
        return appendConditionWithSubQuery(" > ANY ", query);
    }

    public Condition greaterThanAll(QueryBuilder query) {
        return appendConditionWithSubQuery(" > ALL ", query);
    }

    public Condition greaterThanOrEqual(String value) {
        return appendConditionWithValue(" >= ", value);
    }

    public Condition greaterThanOrEqual(Integer value) {
        return appendConditionWithValue(" >= ", value);
    }

    public Condition greaterThanOrEqual(Double value) {
        return appendConditionWithValue(" >= ", value);
    }

    public Condition greaterThanOrEqualAny(QueryBuilder query) {
        return appendConditionWithSubQuery(" >= ANY ", query);
    }

    public Condition greaterThanOrEqualAll(QueryBuilder query) {
        return appendConditionWithSubQuery(" >= ALL ", query);
    }

    public Condition lesserThan(String value) {
        return appendConditionWithValue(" < ", value);
    }

    public Condition lesserThan(Integer value) {
        return appendConditionWithValue(" < ", value);
    }

    public Condition lesserThan(Double value) {
        return appendConditionWithValue(" < ", value);
    }

    public Condition lesserThanAny(QueryBuilder query) {
        return appendConditionWithSubQuery(" < ANY ", query);
    }

    public Condition lesserThanAll(QueryBuilder query) {
        return appendConditionWithSubQuery(" < ALL ", query);
    }

    public Condition lesserThanOrEqual(String value) {
        return appendConditionWithValue(" <= ", value);
    }

    public Condition lesserThanOrEqual(Integer value) {
        return appendConditionWithValue(" <= ", value);
    }

    public Condition lesserThanOrEqual(Double value) {
        return appendConditionWithValue(" <= ", value);
    }

    public Condition lesserThanOrEqualAny(QueryBuilder query) {
        return appendConditionWithSubQuery(" <= ANY ", query);
    }

    public Condition lesserThanOrEqualAll(QueryBuilder query) {
        return appendConditionWithSubQuery(" <= ALL ", query);
    }

    public Condition startsWith(String pattern) {
        validator.validate(pattern);
        String startsWith = pattern.concat("%");
        query.append(" LIKE ");
        QueryAppender.appendStringValue(query, startsWith);
        return getCondition();
    }

    public Condition endsWith(String pattern) {
        validator.validate(pattern);
        String endsWith = "%".concat(pattern);
        query.append(" LIKE ");
        QueryAppender.appendStringValue(query, endsWith);
        return getCondition();
    }

    public Condition contains(String pattern) {
        validator.validate(pattern);
        String contains = "%".concat(pattern).concat("%");
        query.append(" LIKE ");
        QueryAppender.appendStringValue(query, contains);
        return getCondition();
    }

    public Condition isInSub(QueryBuilder query) {
        return appendConditionWithSubQuery(" IN ", query);
    }

    public Condition isBetween(String lower, String higher) {
        appendConditionWithValue(" BETWEEN ", lower);
        return appendConditionWithValue(" AND ", higher);
    }

    public Condition isBetween(Integer lower, Integer higher) {
        appendConditionWithValue(" BETWEEN ", lower);
        return appendConditionWithValue(" AND ", higher);
    }

    public Condition isBetween(Double lower, Double higher) {
        appendConditionWithValue(" BETWEEN ", lower);
        return appendConditionWithValue(" AND ", higher);
    }

    public Condition isNull() {
        query.append(" IS NULL");
        return getCondition();
    }

    public Condition isNotNull() {
        query.append(" IS NOT NULL");
        return getCondition();
    }

    public Condition isIn(String ...listOfValues) {
        validateList(listOfValues);
        query.append(" IN ");
        QueryAppender.appendListOfValues(query, listOfValues);
        return getCondition();
    }

    public Condition isIn(int ...listOfValues) {
        query.append(" IN ");
        QueryAppender.appendListOfValues(query, listOfValues);
        return getCondition();
    }

    public Condition isIn(double ...listOfValues) {
        query.append(" IN ");
        QueryAppender.appendListOfValues(query, listOfValues);
        return getCondition();
    }

    private Condition appendConditionWithValue(String condition, String value) {
        validator.validate(value);
        query.append(condition);
        QueryAppender.appendStringValue(query, value);
        return getCondition();
    }

    private Condition appendConditionWithValue(String condition, Integer value) {
        query.append(condition);
        query.append(value);
        return getCondition();
    }

    private Condition appendConditionWithValue(String condition, Double value) {
        query.append(condition);
        query.append(value);
        return getCondition();
    }

    private Condition appendConditionWithSubQuery(String condition, QueryBuilder subQuery) {
        query.append(condition);
        query.append("(");
        query.append(subQuery.build());
        query.append(")");
        return getCondition();
    }

    private void validateList(String[] columns) {
        Arrays.stream(columns).forEach(column -> validator.validate(column));
    }

    private Condition getCondition() {
        return new Condition(query);
    }
}
