package builder.query.select.order;

import builder.appender.ValueAppender;
import builder.query.select.TerminalSelectOperation;
import query.Statement;

public class Limit extends TerminalSelectOperation {

    public Limit(Statement statement) {
        super(statement);
    }

    /**
     * Appends 'LIMIT to' into query string 'SELECT ... LIMIT to'.
     *
     * @param to row count to limit query results
     *
     * @return Offset class which can be use to
     * set OFFSET or terminate query
     */
    public Offset limit(int to) {
        statement.append(" LIMIT ");
        ValueAppender.appendIntParam(statement, to);
        return new Offset(statement);
    }
}
