package builder.query.select.order;

import builder.appender.ValueAppender;
import builder.query.select.TerminalSelectOperation;
import query.Clause;

public class Offset extends TerminalSelectOperation {

    public Offset(Clause clause) {
        super(clause);
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
        clause.append(" OFFSET ");
        ValueAppender.appendIntParam(clause, rows);
        return new TerminalSelectOperation(clause);
    }
}
