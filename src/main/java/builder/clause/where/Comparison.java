package builder.clause.where;

import builder.Builder;
import builder.SQLStringBuilder;

public class Comparison extends SQLStringBuilder {

    public Comparison(StringBuilder builder) {
        this.builder = builder;
    }

    public Condition equals(String value) {
        return appendConditionWithValue(" = ", value);
    }

    public Condition equals(Integer value) {
        return appendConditionWithValue(" = ", value);
    }

    public Condition equalsAny(Builder query) {
        return appendConditionWithSubQuery(" = ANY ", query);
    }

    public Condition greaterThan(String value) {
        return appendConditionWithValue(" > ", value);
    }

    public Condition greaterThan(Integer value) {
        return appendConditionWithValue(" > ", value);
    }

    public Condition greaterThanAny(Builder query) {
        return appendConditionWithSubQuery(" > ANY ", query);
    }

    public Condition greaterThanOrEqual(String value) {
        return appendConditionWithValue(" >= ", value);
    }

    public Condition greaterThanOrEqual(Integer value) {
        return appendConditionWithValue(" >= ", value);
    }

    public Condition greaterThanOrEqualAny(Builder query) {
        return appendConditionWithSubQuery(" >= ANY ", query);
    }

    public Condition lesserThan(String value) {
        return appendConditionWithValue(" < ", value);
    }

    public Condition lesserThan(Integer value) {
        return appendConditionWithValue(" < ", value);
    }

    public Condition lesserThanAny(Builder query) {
        return appendConditionWithSubQuery(" < ANY ", query);
    }

    public Condition lesserThanOrEqual(String value) {
        return appendConditionWithValue(" <= ", value);
    }

    public Condition lesserThanOrEqual(Integer value) {
        return appendConditionWithValue(" <= ", value);
    }

    public Condition lesserThanOrEqualAny(Builder query) {
        return appendConditionWithSubQuery(" <= ANY ", query);
    }

    public Condition isBetween(String lower, String higher) {
        appendConditionWithValue(" BETWEEN ", lower);
        return appendConditionWithValue(" AND ", higher);
    }

    public Condition isBetween(Integer lower, Integer higher) {
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

    public Condition isLike(String pattern) {
        return appendConditionWithValue(" LIKE ", pattern);
    }

    public Condition isIn(String ...listOfValue) {
        append(" IN (");
        appendStringValue(listOfValue[0]);

        for (int i = 1; i < listOfValue.length; i++) {
            append(", ");
            appendStringValue(listOfValue[i]);
        }

        append(")");
        return getCondition();
    }

    public Condition isIn(int ...listOfValue) {
        append(" IN (");
        append(listOfValue[0]);

        for (int i = 1; i < listOfValue.length; i++) {
            append(", ");
            append(listOfValue[i]);
        }

        append(")");
        return getCondition();
    }

    public Condition isInSub(Builder query) {
        return appendConditionWithSubQuery(" IN ", query);
    }

    private Condition appendConditionWithValue(String condition, String value) {
        append(condition);
        appendStringValue(value);
        return getCondition();
    }

    private Condition appendConditionWithValue(String condition, Integer value) {
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

    private Condition appendStringValue(String value) {
        append("'");
        append(value);
        append("'");
        return getCondition();
    }

    private Condition getCondition() {
        return new Condition(this.builder);
    }
}
