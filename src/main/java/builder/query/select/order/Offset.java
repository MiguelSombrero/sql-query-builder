package builder.query.select.order;

import builder.appender.ValueAppender;
import builder.query.select.TerminalSelectOperation;
import query.SelectQuery;

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
        ValueAppender.appendIntParam(query, rows);
        return new TerminalSelectOperation(query);
    }
}
