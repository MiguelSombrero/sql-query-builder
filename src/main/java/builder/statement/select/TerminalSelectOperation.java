package builder.statement.select;

import builder.Query;
import builder.TerminalOperation;

public class TerminalSelectOperation extends TerminalOperation implements SelectQueryBuilder {
    public TerminalSelectOperation(Query query) {
        super(query);
    }
}
