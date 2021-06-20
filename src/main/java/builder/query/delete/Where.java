package builder.query.delete;

import builder.clause.Condition;
import query.DeleteQuery;

public class Where extends TerminalDeleteOperation {

    public Where(DeleteQuery query) {
        super(query);
    }

    /**
     * Appends 'WHERE condition' into
     * 'DELETE table WHERE condition' statement.
     *
     * @param condition WHERE condition to append
     *
     * @return TerminalDeleteOperation class which can be used only
     * to terminate query building
     */
    public TerminalDeleteOperation where(Condition condition) {
        query.append(" WHERE ");
        query.mergeClause(condition.build());
        return new TerminalDeleteOperation(query);
    }
}
