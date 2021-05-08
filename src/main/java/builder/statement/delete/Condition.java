package builder.statement.delete;

import builder.TerminalOperation;

public class Condition extends TerminalOperation {

    public Condition(StringBuilder builder) {
        super(builder);
    }

    public TerminalOperation where(builder.clause.where.Condition clause) {
        append(" WHERE ");
        append(clause.build());
        return new TerminalOperation(this.builder);
    }
}
