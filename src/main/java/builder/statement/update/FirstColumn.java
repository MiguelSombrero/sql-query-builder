package builder.statement.update;

import builder.Query;
import factory.ValidatorFactory;
import validation.Validator;

public class FirstColumn extends Query {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    public FirstColumn(StringBuilder queryString) {
        super(queryString);
    }

    public Value column(String column) {
        validator.validate(column);
        append(column);
        append(" = ");
        return new Value(this.queryString);
    }
}
