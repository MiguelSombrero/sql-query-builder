package builder.query.select.order;

import builder.appender.ValueAppender;
import builder.query.select.TerminalSelectOperation;
import query.Clause;

public class Limit extends TerminalSelectOperation {

    public Limit(Clause clause) {
        super(clause);
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
        clause.append(" LIMIT ");
        ValueAppender.appendIntParam(clause, to);
        return new Offset(clause);
    }
}
