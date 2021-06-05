package builder.statement.update;

import builder.utils.StringAppender;
import query.dml.UpdateQuery;

public class Table {
    private StringAppender stringAppender;

    private UpdateQuery query;

    public Table(UpdateQuery query) {
        this.query = query;
        this.stringAppender = new StringAppender(query);
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
        query.append(" SET ");
        return new FirstColumn(query);
    }
}
