package builder.statement.delete;

import builder.TerminalOperation;

public class Condition extends TerminalOperation {

    public Condition(StringBuilder queryString) {
        super(queryString);
    }

    public TerminalOperation where(builder.condition.Condition clause) {
        append(" WHERE ");
        append(clause.build());
        return new TerminalOperation(this.queryString);
    }
}
