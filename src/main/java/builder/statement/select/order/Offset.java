package builder.statement.select.order;

import builder.query.Query;
import builder.statement.select.TerminalSelectOperation;

public class Offset extends TerminalSelectOperation {

    public Offset(Query query) {
        super(query);
    }

    /**
     * Appends 'OFFSET rows' into query string 'SELECT ... LIMIT to OFFSET rows'.
     *
     * @param rows row count to offset query results
     *
     * @return TerminalOperation class which terminates
     * query
     */
    public TerminalSelectOperation offset(int rows) {
        query.append(" OFFSET ");
        query.append(rows);
        return new TerminalSelectOperation(query);
    }
}
