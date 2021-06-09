package builder.statement.drop;

import builder.appender.StringAppender;
import query.ddl.DropQuery;

public class Drop {
    private StringAppender stringAppender;

    private DropQuery query;

    public Drop(DropQuery query) {
        this.query = query;
        this.stringAppender = new StringAppender(query);
    }

    /**
     * Validates user input and appends 'TABLE table'
     * into 'DROP TABLE table' statement.
     *
     * @param table Table name to be appended
     *
     * @return TerminalDropOperation class which can be used
     * to terminate DROP query building
     */
    public TerminalDropOperation table(String table) {
        query.append("TABLE ");
        stringAppender.validateAndAppend(table);
        return new TerminalDropOperation(query);
    }

    /**
     * Validates user input and appends 'DATABASE database'
     * into 'DROP DATABASE database' statement.
     *
     * @param database Database name to be dropped
     *
     * @return TerminalDropOperation class which can be used
     * to terminate DROP query building
     */
    public TerminalDropOperation database(String database) {
        query.append("DATABASE ");
        stringAppender.validateAndAppend(database);
        return new TerminalDropOperation(query);
    }
}
