package builder.select.column;

import builder.select.table.Table;

public class AliasedColumn extends ColumnTemplate {

    public AliasedColumn(StringBuilder builder) {
        super(builder);
    }

    public Table from(String table) {
        append(" FROM ");
        append(table);
        return new Table(this.builder);
    }

    @Override
    protected void addCommaAfterFirstValue() {
        append(", ");
    }
}
