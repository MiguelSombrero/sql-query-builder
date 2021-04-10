package builder.clause;

import builder.SQLQueryBuilder;

public class From extends SQLQueryBuilder {

    public From(StringBuilder builder) {
        this.builder = builder;
    }

    public From alias(String alias) {
        append(" ");
        append(alias);
        return this;
    }

    public Condition where(String value) {
        append(" WHERE ");
        append(value);
        return new Condition(this.builder);
    }
}
