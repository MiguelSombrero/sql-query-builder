package builder.query.select.order;

import builder.appender.ValueAppender;
import builder.query.select.TerminalSelectOperation;
import query.Statement;

public class Offset extends TerminalSelectOperation {

    public Offset(Statement statement) {
        super(statement);
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
        statement.append(" OFFSET ");
        ValueAppender.appendIntParam(statement, rows);
        return new TerminalSelectOperation(statement);
    }
}
