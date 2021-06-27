package builder.query.insert;

import builder.appender.StringAppender;
import query.Statement;

public class InsertTable {
    private Statement statement;

    public InsertTable(Statement statement) {
        this.statement = statement;
    }

    /**
     * Validates user input and appends
     * 'table' into 'INSERT INTO table' statement.
     *
     * @param table Table name to be appended
     *
     * @return Insert class which can be used to
     * insert column(s) into INSERT INTO statement.
     */
    public Insert table(String table) {
        StringAppender.validateAndAppend(statement, table);
        statement.append(" ");
        return new Insert(statement);
    }
}
