package builder.clause;

import builder.SQLQueryBuilder;

public class Condition extends SQLQueryBuilder {

    public Condition(StringBuilder builder) {
        this.builder = builder;
    }

    public Where equals(String value) {
        return appendConditionWithValue(" = ", value);
    }

    public Where equals(Integer value) {
        return appendConditionWithValue(" = ", value);
    }

    public Where greaterThan(String value) {
        return appendConditionWithValue(" > ", value);
    }

    public Where greaterThan(Integer value) {
        return appendConditionWithValue(" > ", value);
    }

    public Where greaterThanOrEqual(String value) {
        return appendConditionWithValue(" >= ", value);
    }

    public Where greaterThanOrEqual(Integer value) {
        return appendConditionWithValue(" >= ", value);
    }

    public Where lesserThan(String value) {
        return appendConditionWithValue(" < ", value);
    }

    public Where lesserThan(Integer value) {
        return appendConditionWithValue(" < ", value);
    }

    private Where appendConditionWithValue(String condition, String value) {
        append(condition);
        append("'");
        append(value);
        append("'");
        return new Where(this.builder);
    }

    private Where appendConditionWithValue(String condition, Integer value) {
        append(condition);
        append(value);
        return new Where(this.builder);
    }
}
