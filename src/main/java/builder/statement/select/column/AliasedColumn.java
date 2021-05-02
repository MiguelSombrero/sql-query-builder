package builder.statement.select.column;

import builder.statement.select.table.FirstTable;

public class AliasedColumn extends ColumnTemplate {

    public AliasedColumn(StringBuilder builder) {
        super(builder);
    }

    public FirstTable from() {
        append(" FROM ");
        return new FirstTable(this.builder);
    }

    @Override
    protected void addCommaAfterFirstValue() {
        append(", ");
    }
}
