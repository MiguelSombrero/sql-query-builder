package builder.statement.drop;

import query.SQLQuery;
import builder.TerminalOperation;
import builder.utils.StringAppender;

public class Drop {
    private StringAppender stringAppender;

    private SQLQuery SQLQuery;

    public Drop(SQLQuery SQLQuery) {
        this.SQLQuery = SQLQuery;
        this.stringAppender = new StringAppender(SQLQuery);
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
        SQLQuery.append("TABLE ");
        stringAppender.validateAndAppend(table);
        return new TerminalOperation(SQLQuery);
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
        SQLQuery.append("DATABASE ");
        stringAppender.validateAndAppend(database);
        return new TerminalOperation(SQLQuery);
    }
}
