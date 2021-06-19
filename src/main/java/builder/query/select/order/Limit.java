package builder.query.select.order;

import builder.appender.ValueAppender;
import builder.query.select.TerminalSelectOperation;
import query.SelectQuery;

public class Limit extends TerminalSelectOperation {

    public Limit(SelectQuery query) {
        super(query);
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
        query.append(" LIMIT ");
        ValueAppender.appendIntParam(query, to);
        return new Offset(query);
    }
}
