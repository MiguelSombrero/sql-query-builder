package builder.statement.delete;

import builder.TerminalDMLOperation;
import builder.TerminalOperation;
import builder.condition.Condition;
import query.DMLQuery;

public class Where extends TerminalDMLOperation {

    public Where(DMLQuery query) {
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
        query.append(condition.build().toString());
        return new TerminalOperation(query);
    }
}
