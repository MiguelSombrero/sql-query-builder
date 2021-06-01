package builder.statement.insert;

import query.DMLQuery;
import builder.utils.StringAppender;

public class Table {
    private StringAppender stringAppender;

    private DMLQuery query;

    public Table(DMLQuery query) {
        this.query = query;
        this.stringAppender = new StringAppender(query);
    }

    /**
     * Validates user input and appends
     * 'table' into 'INSERT INTO table' statement.
     *
     * @param table Table name to be appended
     *
     * @return Insert class which can be used to
     * insert column(s) into INSERT INTO statement.
     */
    public Insert table(String table) {
        stringAppender.validateAndAppend(table);
        query.append(" ");
        return new Insert(query);
    }
}
