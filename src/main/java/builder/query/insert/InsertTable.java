package builder.query.insert;

import builder.appender.StringAppender;
import query.Clause;

public class InsertTable {
    private Clause clause;

    public InsertTable(Clause clause) {
        this.clause = clause;
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
        StringAppender.validateAndAppend(clause, table);
        clause.append(" ");
        return new Insert(clause);
    }
}
