package builder.statement.create.index;

import builder.SQLStringAppender;
import builder.TerminalOperation;

import javax.xml.bind.ValidationException;

public class IndexedColumn extends SQLStringAppender {

    public IndexedColumn(StringBuilder queryString) {
        super(queryString);
    }

    public TerminalOperation columns(String ...listOfColumns) throws ValidationException {
        append(" ");
        validateAndAppendList(listOfColumns);
        return new TerminalOperation(this.queryString);
    }
}
