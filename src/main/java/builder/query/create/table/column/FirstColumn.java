package builder.query.create.table.column;

import builder.appender.StringAppender;
import query.Clause;

public class FirstColumn {
    private Clause clause;

    public FirstColumn(Clause clause) {
        this.clause = clause;
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
        StringAppender.validateAndAppend(clause, column);
        return new ColumnType(clause);
    }
}
