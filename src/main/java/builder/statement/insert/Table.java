package builder.statement.insert;

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
        SQLQuery.append(" ");
        return new Insert(SQLQuery);
    }
}
