package builder.query.update;

import builder.appender.StringAppender;
import query.Clause;

public class UpdateTable {
    private Clause clause;

    public UpdateTable(Clause clause) {
        this.clause = clause;
    }

    /**
     * Validates user input and appends 'table SET' into
     * 'UPDATE table SET column = ...' statements.
     *
     * @param table Table name to be appended
     *
     * @return FirstColumn class which can be used to append
     * columns into UPDATE statement
     */
    public FirstColumn table(String table) {
        StringAppender.validateAndAppend(clause, table);
        clause.append(" SET ");
        return new FirstColumn(clause);
    }
}
