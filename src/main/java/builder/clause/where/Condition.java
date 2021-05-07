package builder.clause.where;

import builder.TerminalOperation;

public class Condition extends TerminalOperation {

    public Condition(StringBuilder builder) {
        super(builder);
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
