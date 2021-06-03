package builder.statement.drop;

import builder.TerminalDDLOperation;
import builder.utils.StringAppender;
import query.ddl.DDLQuery;

public class Drop {
    private StringAppender stringAppender;

    private DDLQuery query;

    public Drop(DDLQuery query) {
        this.query = query;
        this.stringAppender = new StringAppender(query);
    }

    /**
     * Validates user input and appends 'TABLE table'
     * into 'DROP TABLE table' statement.
     *
     * @param table Table name to be appended
     *
     * @return TerminalDDLOperation class which can be used
     * to terminate CREATE and DROP query building
     */
    public TerminalDDLOperation table(String table) {
        query.append("TABLE ");
        stringAppender.validateAndAppend(table);
        return new TerminalDDLOperation(query);
    }

    /**
     * Validates user input and appends 'DATABASE database'
     * into 'DROP DATABASE database' statement.
     *
     * @param database Database name to be dropped
     *
     * @return TerminalDDLOperation class which can be used
     * to terminate CREATE and DROP query building
     */
    public TerminalDDLOperation database(String database) {
        query.append("DATABASE ");
        stringAppender.validateAndAppend(database);
        return new TerminalDDLOperation(query);
    }
}
