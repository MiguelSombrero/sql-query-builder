package builder.statement.select.order;

import builder.TerminalOperation;

public class Limiter extends TerminalOperation {

    public Limiter(StringBuilder builder) {
        super(builder);
    }

    public TerminalOperation limit(int to) {
        append(" LIMIT ");
        append(to);
        return new TerminalOperation(this.builder);
    }
}
