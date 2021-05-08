package builder.statement.insert;

import builder.TerminalOperation;

public abstract class ValueTemplate extends TerminalOperation {

    public ValueTemplate(StringBuilder builder) {
        this.builder = builder;
    }

    public Value value(String value) {
        int index = lastIndexOfRightBracket();
        insert(index, "'");
        insert(index, value);
        insert(index, "'");
        addCommaAfterFirstValue(index);
        return new Value(this.builder);
    }

    public Value value(int value) {
        int index = lastIndexOfRightBracket();
        insert(index, value);
        addCommaAfterFirstValue(index);
        return new Value(this.builder);
    }

    protected abstract void addCommaAfterFirstValue(int index);
}
