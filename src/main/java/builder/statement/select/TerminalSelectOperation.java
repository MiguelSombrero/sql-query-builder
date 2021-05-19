package builder.statement.select;

import builder.query.Query;
import builder.TerminalOperation;

public class TerminalSelectOperation extends TerminalOperation implements SelectQueryBuilder {

    public TerminalSelectOperation(Query query) {
        super(query);
    }
}
