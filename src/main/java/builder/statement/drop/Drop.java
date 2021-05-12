package builder.statement.drop;

import builder.SQLStringAppender;
import builder.TerminalOperation;

public class Drop extends SQLStringAppender {

    public Drop(StringBuilder queryString) {
        super(queryString);
    }

    public TerminalOperation table(String tableName) {
        append("TABLE ");
        append(tableName);
        return new TerminalOperation(this.queryString);
    }

    public TerminalOperation database(String databaseName) {
        append("DATABASE ");
        append(databaseName);
        return new TerminalOperation(this.queryString);
    }
}
