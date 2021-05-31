package builder.statement.update;

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
     * Validates user input and appends 'column1 =' into
     * 'UPDATE table SET column1 = value1, ...' statement.
     *
     * @param column Column name to be appended into UPDATE query
     *
     * @return Value class which is used to append value
     * into selected column
     */
    public Value column(String column) {
        stringAppender.validateAndAppend(column);
        SQLQuery.append(" = ");
        return new Value(SQLQuery);
    }
}
