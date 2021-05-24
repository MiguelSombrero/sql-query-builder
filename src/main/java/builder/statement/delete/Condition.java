package builder.statement.delete;

import builder.Query;
import builder.TerminalOperation;

public class Condition extends TerminalOperation {

    public Condition(Query query) {
        super(query);
    }

    public TerminalOperation where(builder.condition.Condition clause) {
        query.append(" WHERE ");
        query.append(clause.build());
        return new TerminalOperation(query);
    }
}
