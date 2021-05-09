package builder.statement.select;

import builder.TerminalOperation;

public class TerminalSelectOperation extends TerminalOperation implements SelectBuilder {

    public TerminalSelectOperation(StringBuilder builder) {
        super(builder);
    }
}
