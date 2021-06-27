package builder.query.delete;

import builder.statement.Condition;
import query.Statement;

public class Where extends TerminalDeleteOperation {

    public Where(Statement query) {
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
        statement.append(" WHERE ");
        statement.mergeStatement(condition.build());
        return new TerminalDeleteOperation(statement);
    }
}
