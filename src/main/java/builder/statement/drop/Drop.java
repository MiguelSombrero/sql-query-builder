package builder.statement.drop;

import builder.SQLStringBuilder;
import builder.TerminalOperation;

public class Drop extends SQLStringBuilder {

    public Drop(StringBuilder builder) {
        super(builder);
    }

    public TerminalOperation table(String tableName) {
        append("TABLE ");
        append(tableName);
        return new TerminalOperation(this.builder);
    }

    public TerminalOperation database(String databaseName) {
        append("DATABASE ");
        append(databaseName);
        return new TerminalOperation(this.builder);
    }
}
