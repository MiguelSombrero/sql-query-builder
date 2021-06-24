package builder.query.delete;

import builder.appender.StringAppender;
import query.Clause;

public class DeleteTable {
    private Clause clause;

    public DeleteTable(Clause clause) {
        this.clause = clause;
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
        StringAppender.validateAndAppend(clause, table);
        return new Where(clause);
    }
}
