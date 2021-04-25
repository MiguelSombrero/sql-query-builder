package builder.select.conjunction;

import builder.TerminalOperation;
import builder.select.order.FirstGroupBy;
import builder.select.order.FirstOrderBy;

public class Conjunction extends TerminalOperation {

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

    public FirstGroupBy groupBy() {
        append(" GROUP BY ");
        return new FirstGroupBy(this.builder);
    }

    public FirstOrderBy orderBy() {
        append(" ORDER BY ");
        return new FirstOrderBy(this.builder);
    }

    private Negation appendConjuntion(String value) {
        append(value);
        return new Negation(this.builder);
    }
}
