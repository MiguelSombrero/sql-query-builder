package builder.statement.create.table.column;

import query.SQLQuery;
import builder.utils.StringAppender;

public class FirstColumn {
    private StringAppender stringAppender;

    private SQLQuery SQLQuery;

    public FirstColumn(SQLQuery SQLQuery) {
        this.SQLQuery = SQLQuery;
        this.stringAppender = new StringAppender(SQLQuery);
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
        return new ColumnType(SQLQuery);
    }
}
