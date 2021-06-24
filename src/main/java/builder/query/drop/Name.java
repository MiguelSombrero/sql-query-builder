package builder.query.drop;

import builder.appender.StringAppender;
import query.Clause;

public class Name {
    protected Clause clause;

    public Name(Clause clause) {
        this.clause = clause;
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
        StringAppender.validateAndAppend(clause, name);
        return new TerminalDropOperation(clause);
    }
}
