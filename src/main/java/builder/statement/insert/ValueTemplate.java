package builder.statement.insert;

import javax.xml.bind.ValidationException;

public abstract class ValueTemplate extends TerminalInsertOperation {

    public ValueTemplate(StringBuilder queryString) {
        super(queryString);
    }

    public Value value(String value) throws ValidationException {
        addCommaAfterFirstValue();
        validateAndAppendStringValue(value);
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
