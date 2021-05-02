package builder.statement.select.order;

import builder.TerminalOperation;

public class Limit extends TerminalOperation {

    public Limit() {
    }

    public Limit(StringBuilder builder) {
        super(builder);
    }

    public TerminalOperation limit(int to) {
        append(" LIMIT ");
        append(to);
        return new TerminalOperation(this.builder);
    }
}
