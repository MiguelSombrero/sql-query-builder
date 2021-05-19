package builder.statement.drop;

import builder.Query;
import builder.TerminalOperation;
import factory.ValidatorFactory;
import validation.Validator;

public class Drop extends Query {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    public Drop(StringBuilder queryString) {
        super(queryString);
    }

    public TerminalOperation table(String tableName) {
        validator.validate(tableName);
        append("TABLE ");
        append(tableName);
        return new TerminalOperation(this.queryString);
    }

    public TerminalOperation database(String databaseName) {
        validator.validate(databaseName);
        append("DATABASE ");
        append(databaseName);
        return new TerminalOperation(this.queryString);
    }
}
