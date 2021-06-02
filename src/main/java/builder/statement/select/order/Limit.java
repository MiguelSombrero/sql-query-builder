package builder.statement.select.order;

import builder.statement.select.TerminalDQLOperation;
import query.dql.DQLQuery;

public class Limit extends TerminalDQLOperation {

    public Limit(DQLQuery query) {
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
        query.append(to);
        return new Offset(query);
    }
}
