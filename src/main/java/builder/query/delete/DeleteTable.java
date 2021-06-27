package builder.query.delete;

import builder.appender.StringAppender;
import query.Statement;

public class DeleteTable {
    private Statement statement;

    public DeleteTable(Statement statement) {
        this.statement = statement;
    }

    /**
     * Validates user input and appends 'table'
     * into 'DELETE TABLE table' statement.
     *
     * @param table Table name to be appended
     *
     * @return Condition class which can be used to append
     * WHERE clause to the DELETE statement, or terminate
     * query building
     */
    public Where table(String table) {
        StringAppender.validateAndAppend(statement, table);
        return new Where(statement);
    }
}
