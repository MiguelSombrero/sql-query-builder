package builder.statement.insert;

import builder.Query;
import factory.ValidatorFactory;
import validation.Validator;

public class Table {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    private Query query;

    public Table(Query query) {
        this.query = query;
    }

    public Insert table(String tableName) {
        validator.validate(tableName);
        query.append(tableName);
        query.append(" ");
        return new Insert(query);
    }
}
