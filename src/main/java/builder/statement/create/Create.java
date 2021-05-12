package builder.statement.create;

import builder.SQLStringAppender;
import builder.TerminalOperation;
import builder.statement.create.index.Index;
import builder.statement.create.table.column.FirstColumn;

public class Create extends SQLStringAppender {

    public Create(StringBuilder queryString) {
        super(queryString);
    }

    public FirstColumn table(String tableName) {
        append("TABLE ");
        append(tableName);
        append(" (");
        return new FirstColumn(this.queryString);
    }

    public TerminalOperation database(String databaseName) {
        append("DATABASE ");
        append(databaseName);
        return new TerminalOperation(this.queryString);
    }

    public Index index(String indexName) {
        append("INDEX ");
        append(indexName);
        return new Index(this.queryString);
    }
}
