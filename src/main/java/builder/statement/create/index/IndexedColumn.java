package builder.statement.create.index;

import builder.SQLStringAppender;
import builder.TerminalOperation;

public class IndexedColumn extends SQLStringAppender {

    public IndexedColumn(StringBuilder queryString) {
        super(queryString);
    }

    public TerminalOperation columns(String ...listOfColumns) {
        append(" ");
        appendList(listOfColumns);
        return new TerminalOperation(this.queryString);
    }
}
