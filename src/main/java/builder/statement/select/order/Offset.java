package builder.statement.select.order;

import builder.statement.select.TerminalDQLOperation;
import query.dql.DQLQuery;

public class Offset extends TerminalDQLOperation {

    public Offset(DQLQuery query) {
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
    public TerminalDQLOperation offset(int rows) {
        query.append(" OFFSET ");
        query.append(rows);
        return new TerminalDQLOperation(query);
    }
}
