package builder.statement.insert;

public abstract class ValueTemplate extends TerminalInsertOperation {

    public ValueTemplate(StringBuilder queryString) {
        super(queryString);
    }

    public Value value(String value) {
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
