package builder.statement.update;

import builder.Query;
import factory.ValidatorFactory;
import validation.Validator;

public class FirstColumn {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    private Query query;

    public FirstColumn(Query query) {
        this.query = query;
    }

    public Value column(String column) {
        validator.validate(column);
        query.append(column);
        query.append(" = ");
        return new Value(query);
    }
}
