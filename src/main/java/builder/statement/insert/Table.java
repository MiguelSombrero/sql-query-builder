package builder.statement.insert;

import builder.Query;
import factory.ValidatorFactory;
import validation.Validator;

public class Table extends Query {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    public Table(StringBuilder queryString) {
        super(queryString);
    }

    public Insert table(String tableName) {
        validator.validate(tableName);
        append(tableName);
        append(" ");
        return new Insert(this.queryString);
    }
}
