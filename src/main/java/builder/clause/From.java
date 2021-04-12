package builder.clause;

import builder.SQLQueryBuilder;

public class From extends SQLQueryBuilder {

    public From(StringBuilder builder) {
        this.builder = builder;
    }

    public From from(String table) {
        append(", ");
        append(table);
        return new From(this.builder);
    }

    public From alias(String alias) {
        append(" AS ");
        append(alias);
        return this;
    }

    public Condition where(String value) {
        append(" WHERE ");
        append(value);
        return new Condition(this.builder);
    }

    public Join innerJoin(String table) {
        append(" INNER JOIN ");
        append(table);
        return new Join(this.builder);
    }
}
