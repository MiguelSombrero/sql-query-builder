package builder.statement.update;

import builder.SQLStringAppender;
import factory.ValidatorFactory;
import validation.Validator;

public class Table extends SQLStringAppender {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    public Table(StringBuilder queryString) {
        super(queryString);
    }

    public FirstColumn table(String tableName) {
        validator.validate(tableName);
        append(tableName);
        append(" SET ");
        return new FirstColumn(this.queryString);
    }
}
