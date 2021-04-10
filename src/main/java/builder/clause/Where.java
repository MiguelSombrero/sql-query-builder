package builder.clause;

import builder.SQLQueryBuilder;

public class Where extends SQLQueryBuilder {

    public Where(StringBuilder builder) {
        this.builder = builder;
    }

    public Condition and(String value) {
        append(" AND ");
        append(value);
        return new Condition(this.builder);
    }

}
