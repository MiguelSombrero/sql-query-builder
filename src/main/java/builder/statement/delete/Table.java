package builder.statement.delete;

import query.SQLQuery;
import builder.utils.StringAppender;

public class Table {
    private StringAppender stringAppender;

    private SQLQuery SQLQuery;

    public Table(SQLQuery SQLQuery) {
        this.SQLQuery = SQLQuery;
        this.stringAppender = new StringAppender(SQLQuery);
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
        return new Where(SQLQuery);
    }
}
