package builder.statement.select.order;

import builder.TerminalOperation;
import builder.statement.select.TerminalSelectOperation;

public class Limit extends TerminalSelectOperation {

    public Limit(StringBuilder builder) {
        super(builder);
    }

    public TerminalOperation limit(int to) {
        append(" LIMIT ");
        append(to);
        return new TerminalOperation(this.builder);
    }
}
