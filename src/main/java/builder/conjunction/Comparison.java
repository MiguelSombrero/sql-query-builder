package builder.conjunction;

import builder.SQLQueryBuilder;

public class Comparison extends SQLQueryBuilder {

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
}
