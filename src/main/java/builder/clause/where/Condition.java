package builder.clause.where;

import builder.TerminalOperation;

public class Condition extends TerminalOperation {

    public Condition(StringBuilder builder) {
        super(builder);
    }

    public Negation where(String columnValue) {
        append(" WHERE ");
        append(columnValue);
        return new Negation(this.builder);
    }
}
