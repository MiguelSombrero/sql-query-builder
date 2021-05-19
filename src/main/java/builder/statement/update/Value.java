package builder.statement.update;

import builder.Query;
import factory.ValidatorFactory;
import validation.Validator;

public class Value {
    private static Validator validator = ValidatorFactory.exceptionThrowingStringValueValidator();

    private Query query;
    public Value(Query query) {
        this.query = query;
    }

    public Column value(String value) {
        validator.validate(value);
        query.appendStringValue(value);
        return new Column(query);
    }

    public Column value(int value) {
        query.append(value);
        return new Column(query);
    }

    public Column value(double value) {
        query.append(value);
        return new Column(query);
    }
}
