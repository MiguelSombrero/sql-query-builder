package builder.statement.update;

import builder.SQLStringAppender;
import factory.ValidatorFactory;
import validation.Validator;

public class Value extends SQLStringAppender {
    private static Validator validator = ValidatorFactory.exceptionThrowingStringValueValidator();

    public Value(StringBuilder queryString) {
        super(queryString);
    }

    public Column value(String value) {
        validator.validate(value);
        appendStringValue(value);
        return new Column(this.queryString);
    }

    public Column value(int value) {
        append(value);
        return new Column(this.queryString);
    }

    public Column value(double value) {
        append(value);
        return new Column(this.queryString);
    }
}
