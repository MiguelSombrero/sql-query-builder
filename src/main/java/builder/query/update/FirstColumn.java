package builder.query.update;

import builder.appender.StringAppender;
import query.Statement;

public class FirstColumn {
    private Statement statement;

    public FirstColumn(Statement statement) {
        this.statement = statement;
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
        StringAppender.validateAndAppend(statement, column);
        statement.append(" = ");
        return new Value(statement);
    }
}
