package builder.query.create.index;

import builder.query.create.TerminalCreateOperation;
import builder.appender.StringAppender;
import query.Clause;

public class IndexedColumn {
    private Clause clause;

    public IndexedColumn(Clause clause) {
        this.clause = clause;
    }

    /**
     * Validates user input and appends 'column(s)'
     * into 'CREATE INDEX index ON table (column(s))' statement.
     *
     * @param listOfColumns List of columns index is being created
     *
     * @return TerminalCreateOperation which can be used only
     * to terminate query building and return CreateQuery
     */
    public TerminalCreateOperation columns(String ...listOfColumns) {
        clause.append(" ");
        StringAppender.validateAndAppendList(clause, listOfColumns);
        return new TerminalCreateOperation(clause);
    }
}
