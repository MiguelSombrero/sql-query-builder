package builder.statement.create.index;

import builder.statement.create.TerminalCreateOperation;
import builder.appender.StringAppender;
import query.ddl.CreateQuery;

public class IndexedColumn {
    private StringAppender stringAppender;

    private CreateQuery query;

    public IndexedColumn(CreateQuery query) {
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
    public TerminalCreateOperation columns(String ...listOfColumns) {
        query.append(" ");
        stringAppender.validateAndAppendList(listOfColumns);
        return new TerminalCreateOperation(query);
    }
}
