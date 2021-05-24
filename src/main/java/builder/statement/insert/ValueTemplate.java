package builder.statement.insert;

import builder.Query;
import builder.utils.StringValueAppender;

public abstract class ValueTemplate extends TerminalInsertOperation {
    private StringValueAppender stringValueAppender;

    public ValueTemplate(Query query) {
        super(query);
        this.stringValueAppender = new StringValueAppender(query);
    }

    public Value value(String value) {
        addCommaAfterFirstValue();
        stringValueAppender.validateAndAppend(value);
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
