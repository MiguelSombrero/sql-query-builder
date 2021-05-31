package builder.statement.create.index;

import query.SQLQuery;
import builder.TerminalOperation;
import builder.utils.StringAppender;

public class IndexedColumn {
    private StringAppender stringAppender;

    private SQLQuery SQLQuery;

    public IndexedColumn(SQLQuery SQLQuery) {
        this.SQLQuery = SQLQuery;
        this.stringAppender = new StringAppender(SQLQuery);
    }

    /**
     * Validates user input and appends 'column(s)'
     * into 'CREATE INDEX index ON table (column(s))' statement.
     *
     * @param listOfColumns List of columns index is being created
     *
     * @return TerminalOperation which can be used only
     * to terminate query building
     */
    public TerminalOperation columns(String ...listOfColumns) {
        SQLQuery.append(" ");
        stringAppender.validateAndAppendList(listOfColumns);
        return new TerminalOperation(SQLQuery);
    }
}
