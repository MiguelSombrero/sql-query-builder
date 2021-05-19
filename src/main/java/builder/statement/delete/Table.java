package builder.statement.delete;

import builder.Query;
import factory.ValidatorFactory;
import validation.Validator;

public class Table {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    private Query query;

    public Table(Query query) {
        this.query = query;
    }

    public Condition table(String tableName) {
        validator.validate(tableName);
        query.append(tableName);
        return new Condition(query);
    }
}
