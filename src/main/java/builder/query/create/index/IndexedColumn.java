package builder.query.create.index;

import builder.query.create.TerminalCreateOperation;
import builder.appender.StringAppender;
import query.CreateQuery;

public class IndexedColumn {
    private CreateQuery query;

    public IndexedColumn(CreateQuery query) {
        this.query = query;
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
        StringAppender.validateAndAppendList(query, listOfColumns);
        return new TerminalCreateOperation(query);
    }
}
