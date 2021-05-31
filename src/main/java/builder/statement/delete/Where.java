package builder.statement.delete;

import query.SQLQuery;
import builder.TerminalOperation;
import builder.condition.Condition;

public class Where extends TerminalOperation {

    public Where(SQLQuery SQLQuery) {
        super(SQLQuery);
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
        SQLQuery.append(" WHERE ");
        SQLQuery.append(condition.build());
        return new TerminalOperation(SQLQuery);
    }
}
