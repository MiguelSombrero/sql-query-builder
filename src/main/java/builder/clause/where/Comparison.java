package builder.clause.where;

import builder.SQLStringBuilder;

public class Comparison extends SQLStringBuilder {

    public Comparison(StringBuilder builder) {
        this.builder = builder;
    }

    public Conjunction equals(String value) {
        return appendConditionWithValue(" = ", value);
    }

    public Conjunction equals(Integer value) {
        return appendConditionWithValue(" = ", value);
    }

    public Conjunction greaterThan(String value) {
        return appendConditionWithValue(" > ", value);
    }

    public Conjunction greaterThan(Integer value) {
        return appendConditionWithValue(" > ", value);
    }

    public Conjunction greaterThanOrEqual(String value) {
        return appendConditionWithValue(" >= ", value);
    }

    public Conjunction greaterThanOrEqual(Integer value) {
        return appendConditionWithValue(" >= ", value);
    }

    public Conjunction lesserThan(String value) {
        return appendConditionWithValue(" < ", value);
    }

    public Conjunction lesserThan(Integer value) {
        return appendConditionWithValue(" < ", value);
    }

    public Conjunction lesserThanOrEqual(String value) {
        return appendConditionWithValue(" <= ", value);
    }

    public Conjunction lesserThanOrEqual(Integer value) {
        return appendConditionWithValue(" <= ", value);
    }

    public Conjunction isBetween(String lower, String higher) {
        appendConditionWithValue(" BETWEEN ", lower);
        return appendConditionWithValue(" AND ", higher);
    }

    public Conjunction isBetween(Integer lower, Integer higher) {
        appendConditionWithValue(" BETWEEN ", lower);
        return appendConditionWithValue(" AND ", higher);
    }

    public Conjunction isNull() {
        append(" IS NULL");
        return new Conjunction(this.builder);
    }

    public Conjunction isNotNull() {
        append(" IS NOT NULL");
        return new Conjunction(this.builder);
    }

    public Conjunction isLike(String pattern) {
        return appendConditionWithValue(" LIKE ", pattern);
    }

    public Conjunction isIn(String ...listOfValue) {
        append(" IN (");
        appendValue(listOfValue[0]);

        for (int i = 1; i < listOfValue.length; i++) {
            append(", ");
            appendValue(listOfValue[i]);
        }

        append(")");
        return new Conjunction(this.builder);
    }

    public Conjunction isIn(int ...listOfValue) {
        append(" IN (");
        append(listOfValue[0]);

        for (int i = 1; i < listOfValue.length; i++) {
            append(", ");
            append(listOfValue[i]);
        }

        append(")");
        return new Conjunction(this.builder);
    }

    private Conjunction appendConditionWithValue(String condition, String value) {
        append(condition);
        append("'");
        append(value);
        append("'");
        return new Conjunction(this.builder);
    }

    private Conjunction appendConditionWithValue(String condition, Integer value) {
        append(condition);
        append(value);
        return new Conjunction(this.builder);
    }

    private Conjunction appendValue(String value) {
        append("'");
        append(value);
        append("'");
        return new Conjunction(this.builder);
    }
}
