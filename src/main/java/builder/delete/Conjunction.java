package builder.delete;

import builder.TerminalOperation;

public class Conjunction extends TerminalOperation {

    public Conjunction(StringBuilder builder) {
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
