package builder.condition;

import builder.Builder;
import builder.SQLStringAppender;
import factory.ValidatorFactory;
import validation.Validator;

public class Comparison extends SQLStringAppender {
    private static Validator validator = ValidatorFactory.exceptionThrowingStringOrDateValidator();

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
        String startsWith = pattern.concat("%");
        return appendConditionWithValue(" LIKE ", startsWith);
    }

    public Condition endsWith(String pattern) {
        String endsWith = "%".concat(pattern);
        return appendConditionWithValue(" LIKE ", endsWith);
    }

    public Condition contains(String pattern) {
        String endsWith = "%".concat(pattern).concat("%");
        return appendConditionWithValue(" LIKE ", endsWith);
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
        append(" IN ");
        validateAndAppendListOfValues(listOfValues);
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
        append(condition);
        validateAndAppendStringValue(value);
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

    private Condition getCondition() {
        return new Condition(this.queryString);
    }
}
