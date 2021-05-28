package builder.statement.create.table.column;

import builder.Query;
import builder.utils.StringAppender;

public class FirstColumn {
    private StringAppender stringAppender;

    private Query query;

    public FirstColumn(Query query) {
        this.query = query;
        this.stringAppender = new StringAppender(query);
    }

    /**
     * Validates user input and appends 'column' into
     * 'CREATE TABLE name (column datatype, ...)'
     * statement.
     *
     * @param column name to be created in table
     *
     * @return ColumnType class which can be used to
     * append column type to selected column
     */
    public ColumnType column(String column) {
        stringAppender.validateAndAppend(column);
        return new ColumnType(query);
    }
}
