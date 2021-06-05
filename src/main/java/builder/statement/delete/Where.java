package builder.statement.delete;

import builder.condition.Condition;
import query.dml.DeleteQuery;

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
     * @return TerminalOperation class which can be used only
     * to terminate query building
     */
    public TerminalDeleteOperation where(Condition condition) {
        query.append(" WHERE ");
        query.mergeSubQuery(condition.build());
        return new TerminalDeleteOperation(query);
    }
}
