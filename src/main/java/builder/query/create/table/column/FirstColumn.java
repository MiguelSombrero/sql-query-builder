package builder.query.create.table.column;

import builder.appender.StringAppender;
import query.CreateQuery;

public class FirstColumn {
    private CreateQuery query;

    public FirstColumn(CreateQuery query) {
        this.query = query;
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
        StringAppender.validateAndAppend(query, column);
        return new ColumnType(query);
    }
}
