package builder.statement.select.order;

import builder.query.Query;
import builder.TerminalOperation;
import builder.statement.select.TerminalSelectOperation;

public class Limit extends TerminalSelectOperation {

    public Limit(Query query) {
        super(query);
    }

    public TerminalOperation limit(int to) {
        query.append(" LIMIT ");
        query.append(to);
        return new TerminalOperation(query);
    }
}
