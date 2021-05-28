package builder.statement.create.index;

import builder.Query;
import builder.TerminalOperation;
import builder.utils.StringAppender;

public class IndexedColumn {
    private StringAppender stringAppender;

    private Query query;

    public IndexedColumn(Query query) {
        this.query = query;
        this.stringAppender = new StringAppender(query);
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
        query.append(" ");
        stringAppender.validateAndAppendList(listOfColumns);
        return new TerminalOperation(query);
    }
}
