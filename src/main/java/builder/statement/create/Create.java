package builder.statement.create;

import builder.SQLStringBuilder;
import builder.TerminalOperation;

public class Create extends SQLStringBuilder {

    public Create(StringBuilder builder) {
        super(builder);
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

    public Index index(String indexName) {
        append("INDEX ");
        append(indexName);
        return new Index(this.builder);
    }
}
