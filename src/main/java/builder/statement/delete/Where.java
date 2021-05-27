package builder.statement.delete;

import builder.Query;
import builder.TerminalOperation;
import builder.condition.Condition;

public class Where extends TerminalOperation {

    public Where(Query query) {
        super(query);
    }

    /**
     * Appends 'WHERE condition' into
     * 'DELETE table WHERE condition' statement.
     *
     * @param condition WHERE condition to append
     *
     * @return TerminalOperation class which can be used only
     * to terminate query building
     */
    public TerminalOperation where(Condition condition) {
        query.append(" WHERE ");
        query.append(condition.build());
        return new TerminalOperation(query);
    }
}
