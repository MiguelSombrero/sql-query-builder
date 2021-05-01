package builder.update;

import builder.TerminalOperation;

public abstract class ColumnTemplate extends TerminalOperation {

    public ColumnTemplate(StringBuilder builder) {
        super(builder);
    }

    public Value column(String column) {
        addCommaAfterFirstValue();
        append(column);
        append(" = ");
        return new Value(this.builder);
    }

    protected abstract void addCommaAfterFirstValue();
}
