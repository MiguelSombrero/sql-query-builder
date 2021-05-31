package builder.statement.update;

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
     * Validates user input and appends 'table SET' into
     * 'UPDATE table SET column = ...' statements.
     *
     * @param table Table name to be appended
     *
     * @return FirstColumn class which can be used to append
     * columns into UPDATE statement
     */
    public FirstColumn table(String table) {
        stringAppender.validateAndAppend(table);
        SQLQuery.append(" SET ");
        return new FirstColumn(SQLQuery);
    }
}
