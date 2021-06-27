package builder.query.update;

import builder.appender.StringAppender;
import query.Statement;

public class UpdateTable {
    private Statement statement;

    public UpdateTable(Statement statement) {
        this.statement = statement;
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
        StringAppender.validateAndAppend(statement, table);
        statement.append(" SET ");
        return new FirstColumn(statement);
    }
}
