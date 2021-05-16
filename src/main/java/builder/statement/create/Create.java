package builder.statement.create;

import builder.SQLStringAppender;
import builder.TerminalOperation;
import builder.statement.create.index.Index;
import builder.statement.create.table.column.FirstColumn;

import javax.xml.bind.ValidationException;

public class Create extends SQLStringAppender {

    public Create(StringBuilder queryString) {
        super(queryString);
    }

    public FirstColumn table(String tableName) throws ValidationException {
        append("TABLE ");
        validateAndAppend(tableName);
        append(" (");
        return new FirstColumn(this.queryString);
    }

    public TerminalOperation database(String databaseName) throws ValidationException {
        append("DATABASE ");
        validateAndAppend(databaseName);
        return new TerminalOperation(this.queryString);
    }

    public Index index(String indexName) throws ValidationException {
        append("INDEX ");
        validateAndAppend(indexName);
        return new Index(this.queryString);
    }
}
