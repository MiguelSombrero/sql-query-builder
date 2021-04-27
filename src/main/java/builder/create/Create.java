package builder.create;

import builder.SQLStringBuilder;
import builder.TerminalOperation;

public class Create extends SQLStringBuilder {

    public Create(StringBuilder builder) {
        this.builder = builder;
    }

    public FirstColumn table(String tableName) {
        append("TABLE ");
        append(tableName);
        append(" ()");
        return new FirstColumn(this.builder);
    }

    public TerminalOperation database(String databaseName) {
        append("DATABASE ");
        append(databaseName);
        return new TerminalOperation(this.builder);
    }
}
