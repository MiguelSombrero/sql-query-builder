package builder.query.create.table.column;

import builder.appender.StringAppender;
import query.Statement;

public class FirstColumn {
    private Statement statement;

    public FirstColumn(Statement statement) {
        this.statement = statement;
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
        StringAppender.validateAndAppend(statement, column);
        return new ColumnType(statement);
    }
}
