package builder.clause;

import builder.SQLQueryBuilder;

public class Condition extends SQLQueryBuilder {

    public Condition(StringBuilder builder) {
        this.builder = builder;
    }

    public Where equals(String value) {
        append(" = ");
        append("'");
        append(value);
        append("'");
        return new Where(this.builder);
    }

    public Where equals(Integer value) {
        append(" = ");
        append(value);
        return new Where(this.builder);
    }

    public Where greaterThan(String value) {
        append(" > ");
        append("'");
        append(value);
        append("'");
        return new Where(this.builder);
    }

    public Where greaterThan(Integer value) {
        append(" > ");
        append(value);
        return new Where(this.builder);
    }

    public Where lesserThan(String value) {
        append(" < ");
        append("'");
        append(value);
        append("'");
        return new Where(this.builder);
    }

    public Where lesserThan(Integer value) {
        append(" < ");
        append(value);
        return new Where(this.builder);
    }
}
