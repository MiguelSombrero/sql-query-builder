package builder.condition;

import builder.Builder;
import builder.SQLQuery;
import factory.ValidatorFactory;
import validation.Validator;

import java.util.Arrays;

public class Comparison extends SQLQuery {
    private static Validator validator = ValidatorFactory.exceptionThrowingStringValueValidator();

    public Comparison(StringBuilder queryString) {
        super(queryString);
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

    public Condition equalsAny(Builder query) {
        return appendConditionWithSubQuery(" = ANY ", query);
    }

    public Condition equalsAll(Builder query) {
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

    public Condition greaterThanAny(Builder query) {
        return appendConditionWithSubQuery(" > ANY ", query);
    }

    public Condition greaterThanAll(Builder query) {
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

    public Condition greaterThanOrEqualAny(Builder query) {
        return appendConditionWithSubQuery(" >= ANY ", query);
    }

    public Condition greaterThanOrEqualAll(Builder query) {
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

    public Condition lesserThanAny(Builder query) {
        return appendConditionWithSubQuery(" < ANY ", query);
    }

    public Condition lesserThanAll(Builder query) {
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

    public Condition lesserThanOrEqualAny(Builder query) {
        return appendConditionWithSubQuery(" <= ANY ", query);
    }

    public Condition lesserThanOrEqualAll(Builder query) {
        return appendConditionWithSubQuery(" <= ALL ", query);
    }

    public Condition startsWith(String pattern) {
        validator.validate(pattern);
        String startsWith = pattern.concat("%");
        append(" LIKE ");
        appendStringValue(startsWith);
        return getCondition();
    }

    public Condition endsWith(String pattern) {
        validator.validate(pattern);
        String endsWith = "%".concat(pattern);
        append(" LIKE ");
        appendStringValue(endsWith);
        return getCondition();
    }

    public Condition contains(String pattern) {
        validator.validate(pattern);
        String contains = "%".concat(pattern).concat("%");
        append(" LIKE ");
        appendStringValue(contains);
        return getCondition();
    }

    public Condition isInSub(Builder query) {
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
        append(" IS NULL");
        return getCondition();
    }

    public Condition isNotNull() {
        append(" IS NOT NULL");
        return getCondition();
    }

    public Condition isIn(String ...listOfValues) {
        validateList(listOfValues);
        append(" IN ");
        appendListOfValues(listOfValues);
        return getCondition();
    }

    public Condition isIn(int ...listOfValues) {
        append(" IN ");
        appendListOfValues(listOfValues);
        return getCondition();
    }

    public Condition isIn(double ...listOfValues) {
        append(" IN ");
        appendListOfValues(listOfValues);
        return getCondition();
    }

    private Condition appendConditionWithValue(String condition, String value) {
        validator.validate(value);
        append(condition);
        appendStringValue(value);
        return getCondition();
    }

    private Condition appendConditionWithValue(String condition, Integer value) {
        append(condition);
        append(value);
        return getCondition();
    }

    private Condition appendConditionWithValue(String condition, Double value) {
        append(condition);
        append(value);
        return getCondition();
    }

    private Condition appendConditionWithSubQuery(String condition, Builder query) {
        append(condition);
        append("(");
        append(query.build());
        append(")");
        return getCondition();
    }

    private void validateList(String[] columns) {
        Arrays.stream(columns).forEach(column -> validator.validate(column));
    }

    private Condition getCondition() {
        return new Condition(this.queryString);
    }
}
