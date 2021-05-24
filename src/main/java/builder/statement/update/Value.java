package builder.statement.update;

import builder.Query;
import builder.utils.QueryAppender;
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
        QueryAppender.appendStringValue(query, value);
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
