package builder.statement.update;

import builder.query.Query;
import factory.ValidatorFactory;
import validation.Validator;

public class Table {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    private Query query;

    public Table(Query query) {
        this.query = query;
    }

    public FirstColumn table(String tableName) {
        validator.validate(tableName);
        query.append(tableName);
        query.append(" SET ");
        return new FirstColumn(query);
    }
}
