package builder.statement.create;

import builder.SQLQuery;
import builder.TerminalOperation;
import builder.statement.create.index.Index;
import builder.statement.create.table.column.FirstColumn;
import factory.ValidatorFactory;
import validation.Validator;

public class Create extends SQLQuery {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    public Create(StringBuilder queryString) {
        super(queryString);
    }

    public FirstColumn table(String tableName) {
        append("TABLE ");
        validateAndAppendName(tableName);
        append(" (");
        return new FirstColumn(this.queryString);
    }

    public TerminalOperation database(String databaseName) {
        append("DATABASE ");
        validateAndAppendName(databaseName);
        return new TerminalOperation(this.queryString);
    }

    public Index index(String indexName) {
        append("INDEX ");
        validateAndAppendName(indexName);
        return new Index(this.queryString);
    }

    private void validateAndAppendName(String input) {
        validator.validate(input);
        append(input);
    }
}
