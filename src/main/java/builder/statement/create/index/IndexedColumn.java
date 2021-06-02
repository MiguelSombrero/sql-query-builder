package builder.statement.create.index;

import builder.TerminalDDLOperation;
import builder.utils.StringAppender;
import query.ddl.DDLQuery;

public class IndexedColumn {
    private StringAppender stringAppender;

    private DDLQuery query;

    public IndexedColumn(DDLQuery query) {
        this.query = query;
        this.stringAppender = new StringAppender(query);
    }

    /**
     * Validates user input and appends 'column(s)'
     * into 'CREATE INDEX index ON table (column(s))' statement.
     *
     * @param listOfColumns List of columns index is being created
     *
     * @return TerminalDDLOperation which can be used only
     * to terminate query building
     */
    public TerminalDDLOperation columns(String ...listOfColumns) {
        query.append(" ");
        stringAppender.validateAndAppendList(listOfColumns);
        return new TerminalDDLOperation(query);
    }
}
