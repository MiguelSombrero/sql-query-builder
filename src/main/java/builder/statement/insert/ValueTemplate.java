package builder.statement.insert;

import builder.Query;
import factory.ValidatorFactory;
import validation.Validator;

public abstract class ValueTemplate extends TerminalInsertOperation {
    private static Validator validator = ValidatorFactory.exceptionThrowingStringValueValidator();

    public ValueTemplate(Query query) {
        super(query);
    }

    public Value value(String value) {
        validator.validate(value);
        addCommaAfterFirstValue();
        query.appendStringValue(value);
        return new Value(query);
    }

    public Value value(int value) {
        addCommaAfterFirstValue();
        query.append(value);
        return new Value(query);
    }

    public Value value(double value) {
        addCommaAfterFirstValue();
        query.append(value);
        return new Value(query);
    }

    protected abstract void addCommaAfterFirstValue();
}
