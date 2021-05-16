package builder.statement.drop;

import builder.SQLStringAppender;
import builder.TerminalOperation;

import javax.xml.bind.ValidationException;

public class Drop extends SQLStringAppender {

    public Drop(StringBuilder queryString) {
        super(queryString);
    }

    public TerminalOperation table(String tableName) throws ValidationException {
        append("TABLE ");
        validateAndAppend(tableName);
        return new TerminalOperation(this.queryString);
    }

    public TerminalOperation database(String databaseName) throws ValidationException {
        append("DATABASE ");
        validateAndAppend(databaseName);
        return new TerminalOperation(this.queryString);
    }
}
