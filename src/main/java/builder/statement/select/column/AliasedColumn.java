package builder.statement.select.column;

import builder.statement.select.table.From;

public class AliasedColumn extends ColumnTemplate {

    public AliasedColumn(StringBuilder builder) {
        super(builder);
    }

    public From from() {
        append(" FROM ");
        return new From(this.builder);
    }

    @Override
    protected void addCommaAfterFirstValue() {
        append(", ");
    }
}
