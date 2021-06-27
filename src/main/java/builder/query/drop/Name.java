package builder.query.drop;

import builder.appender.StringAppender;
import query.Statement;

public class Name {
    protected Statement statement;

    public Name(Statement statement) {
        this.statement = statement;
    }

    /**
     * Validates user input and appends 'name'
     * into 'DROP TABLE/DATABASE name' statement.
     *
     * @param name Table/Database name to be appended
     *
     * @return TerminalDropOperation class which can be used
     * to terminate DROP query building
     */
    public TerminalDropOperation name(String name) {
        StringAppender.validateAndAppend(statement, name);
        return new TerminalDropOperation(statement);
    }
}
