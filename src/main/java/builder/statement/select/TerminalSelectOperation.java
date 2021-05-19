package builder.statement.select;

import builder.Query;
import builder.TerminalOperation;

public class TerminalSelectOperation extends TerminalOperation implements SelectBuilder {

    public TerminalSelectOperation(Query query) {
        super(query);
    }
}
