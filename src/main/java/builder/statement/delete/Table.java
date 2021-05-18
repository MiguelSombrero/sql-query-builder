package builder.statement.delete;

import builder.SQLStringAppender;
import factory.ValidatorFactory;
import validation.Validator;

public class Table extends SQLStringAppender {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    public Table(StringBuilder queryString) {
        super(queryString);
    }

    public Condition table(String tableName) {
        validator.validate(tableName);
        append(tableName);
        return new Condition(this.queryString);
    }
}
