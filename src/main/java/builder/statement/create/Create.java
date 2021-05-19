package builder.statement.create;

import builder.query.Query;
import builder.TerminalOperation;
import builder.statement.create.index.Index;
import builder.statement.create.table.column.FirstColumn;
import factory.ValidatorFactory;
import validation.Validator;

public class Create {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    private Query query;

    public Create(Query query) {
        this.query = query;
    }

    public FirstColumn table(String tableName) {
        query.append("TABLE ");
        validateAndAppendName(tableName);
        query.append(" (");
        return new FirstColumn(query);
    }

    public TerminalOperation database(String databaseName) {
        query.append("DATABASE ");
        validateAndAppendName(databaseName);
        return new TerminalOperation(query);
    }

    public Index index(String indexName) {
        query.append("INDEX ");
        validateAndAppendName(indexName);
        return new Index(query);
    }

    private void validateAndAppendName(String input) {
        validator.validate(input);
        query.append(input);
    }
}
