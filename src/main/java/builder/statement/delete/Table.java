package builder.statement.delete;

import query.dml.DMLQuery;
import builder.utils.StringAppender;

public class Table {
    private StringAppender stringAppender;

    private DMLQuery query;

    public Table(DMLQuery query) {
        this.query = query;
        this.stringAppender = new StringAppender(query);
    }

    /**
     * Validates user input and appends 'table'
     * into 'DELETE TABLE table' statement.
     *
     * @param table Table name to be appended
     *
     * @return Condition class which can be used to append
     * WHERE clause to the DELETE statement, or terminate
     * query building
     */
    public Where table(String table) {
        stringAppender.validateAndAppend(table);
        return new Where(query);
    }
}
