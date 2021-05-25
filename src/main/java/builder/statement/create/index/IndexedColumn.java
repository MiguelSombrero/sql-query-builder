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

    public TerminalOperation columns(String ...listOfColumns) {
        query.append(" ");
        stringAppender.validateAndAppendList(listOfColumns);
        return new TerminalOperation(query);
    }
}
