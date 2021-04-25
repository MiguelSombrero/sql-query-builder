package builder.create;

import builder.SQLQueryBuilder;

public abstract class ColumnTemplate extends SQLQueryBuilder {

    public ColumnTemplate(StringBuilder builder) {
        this.builder = builder;
    }

    public ColumnType column(String columns) {
        int index = firstIndexOfRightBracket();
        insert(index, columns);
        addCommaAfterFirstValue(index);
        return new ColumnType(this.builder);
    }

    protected abstract void addCommaAfterFirstValue(int index);
}
