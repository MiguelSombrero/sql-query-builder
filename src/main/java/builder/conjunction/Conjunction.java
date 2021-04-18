package builder.conjunction;

import builder.SQLQueryBuilder;

public class Conjunction extends SQLQueryBuilder {

    public Conjunction(StringBuilder builder) {
        this.builder = builder;
    }

    public Negation and(String value) {
        append(" AND ");
        append(value);
        return new Negation(this.builder);
    }

}
