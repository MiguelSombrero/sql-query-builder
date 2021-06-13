package builder.query.update;

import builder.appender.StringAppender;
import query.dml.UpdateQuery;

public class Table {
    private UpdateQuery query;

    public Table(UpdateQuery query) {
        this.query = query;
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
        StringAppender.validateAndAppend(query, table);
        query.append(" SET ");
        return new FirstColumn(query);
    }
}
