package builder.select.column;

import builder.SQLStringBuilder;
import builder.select.table.Table;

public abstract class ColumnTemplate extends SQLStringBuilder {

    public ColumnTemplate(StringBuilder builder) {
        this.builder = builder;
    }

    public Table from(String table) {
        append(" FROM ");
        append(table);
        return new Table(this.builder);
    }

    public Column field(String fieldName) {
        addCommaAfterFirstValue();
        append(fieldName);
        return new Column(this.builder);
    }

    public Column min(String columnName) {
        addCommaAfterFirstValue();
        append("MIN(");
        append(columnName);
        append(")");
        return new Column(this.builder);
    }

    protected abstract void addCommaAfterFirstValue();
}
