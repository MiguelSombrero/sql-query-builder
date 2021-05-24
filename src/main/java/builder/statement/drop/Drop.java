package builder.statement.drop;

import builder.Query;
import builder.TerminalOperation;
import factory.ValidatorFactory;
import validation.Validator;

public class Drop {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    private Query query;

    public Drop(Query query) {
        this.query = query;
    }

    public TerminalOperation table(String tableName) {
        validator.validate(tableName);
        query.append("TABLE ");
        query.append(tableName);
        return new TerminalOperation(query);
    }

    public TerminalOperation database(String databaseName) {
        validator.validate(databaseName);
        query.append("DATABASE ");
        query.append(databaseName);
        return new TerminalOperation(query);
    }
}
