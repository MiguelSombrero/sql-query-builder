package builder.statement.drop;

import query.Query;
import builder.TerminalOperation;
import builder.utils.StringAppender;

public class Drop {
    private StringAppender stringAppender;

    private Query query;

    public Drop(Query query) {
        this.query = query;
        this.stringAppender = new StringAppender(query);
    }

    /**
     * Validates user input and appends 'TABLE table'
     * into 'DROP TABLE table' statement.
     *
     * @param table Table name to be appended
     *
     * @return TerminalOperation class which can be used only
     * to terminate query building
     */
    public TerminalOperation table(String table) {
        query.append("TABLE ");
        stringAppender.validateAndAppend(table);
        return new TerminalOperation(query);
    }

    /**
     * Validates user input and appends 'DATABASE database'
     * into 'DROP DATABASE database' statement.
     *
     * @param database Database name to be dropped
     *
     * @return TerminalOperation class which can be used only
     * to terminate query building
     */
    public TerminalOperation database(String database) {
        query.append("DATABASE ");
        stringAppender.validateAndAppend(database);
        return new TerminalOperation(query);
    }
}
