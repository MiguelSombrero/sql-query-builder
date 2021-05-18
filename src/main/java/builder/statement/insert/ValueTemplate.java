package builder.statement.insert;

import factory.ValidatorFactory;
import validation.Validator;

public abstract class ValueTemplate extends TerminalInsertOperation {
    private static Validator validator = ValidatorFactory.exceptionThrowingStringValueValidator();

    public ValueTemplate(StringBuilder queryString) {
        super(queryString);
    }

    public Value value(String value) {
        validator.validate(value);
        addCommaAfterFirstValue();
        appendStringValue(value);
        return new Value(this.queryString);
    }

    public Value value(int value) {
        addCommaAfterFirstValue();
        append(value);
        return new Value(this.queryString);
    }

    public Value value(double value) {
        addCommaAfterFirstValue();
        append(value);
        return new Value(this.queryString);
    }

    protected abstract void addCommaAfterFirstValue();
}
