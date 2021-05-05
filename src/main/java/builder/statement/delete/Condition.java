package builder.statement.delete;

import builder.TerminalOperation;
import builder.clause.where.Conjunction;

public class Condition extends TerminalOperation {

    public Condition(StringBuilder builder) {
        super(builder);
    }

    public TerminalOperation where(Conjunction clause) {
        append(clause.build());
        return new TerminalOperation(this.builder);
    }
}
