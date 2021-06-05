package builder.statement.select.order;

import builder.statement.select.TerminalSelectOperation;
import query.dql.SelectQuery;

public class Offset extends TerminalSelectOperation {

    public Offset(SelectQuery query) {
        super(query);
    }

    /**
     * Appends 'OFFSET rows' into query string 'SELECT ... LIMIT to OFFSET rows'.
     *
     * @param rows row count to offset query results
     *
     * @return TerminalSelectOperation class which terminates
     * query
     */
    public TerminalSelectOperation offset(int rows) {
        query.append(" OFFSET ");
        query.append(rows);
        return new TerminalSelectOperation(query);
    }
}
