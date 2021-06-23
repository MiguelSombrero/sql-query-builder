package builder.query.drop;

import builder.appender.StringAppender;
import query.DropQuery;

public class Name {
    protected DropQuery query;

    public Name(DropQuery query) {
        this.query = query;
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
        StringAppender.validateAndAppend(query, name);
        return new TerminalDropOperation(query);
    }
}
