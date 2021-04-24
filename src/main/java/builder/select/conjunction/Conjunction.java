package builder.select.conjunction;

import builder.SQLQueryBuilder;

public class Conjunction extends SQLQueryBuilder {

    public Conjunction(StringBuilder builder) {
        this.builder = builder;
    }

    public Negation and(String value) {
        append(" AND ");
        return appendConjuntion(value);
    }

    public Negation or(String value) {
        append(" OR ");
        return appendConjuntion(value);
    }

    private Negation appendConjuntion(String value) {
        append(value);
        return new Negation(this.builder);
    }
}
