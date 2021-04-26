package builder.create;

import builder.TerminalOperation;

public abstract class ColumnTemplate extends TerminalOperation {

    public ColumnTemplate(StringBuilder builder) {
        super(builder);
    }

    public ColumnType column(String columns) {
        int index = lastIndexOfRightBracket();
        insert(index, columns);
        addCommaAfterFirstValue(index);
        return new ColumnType(this.builder);
    }

    protected abstract void addCommaAfterFirstValue(int index);
}
