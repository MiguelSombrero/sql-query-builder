package builder.statement.create.table.column;

import builder.SQLStringAppender;
import factory.ValidatorFactory;
import validation.Validator;

public class FirstColumn extends SQLStringAppender {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    public FirstColumn(StringBuilder queryString) {
        super(queryString);
    }

    public ColumnType column(String column) {
        validator.validate(column);
        append(column);
        return new ColumnType(this.queryString);
    }
}
