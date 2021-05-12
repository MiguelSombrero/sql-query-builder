package builder.statement.select.column;

import builder.statement.select.table.From;

public class AliasedColumn extends ColumnTemplate {

    public AliasedColumn(StringBuilder queryString) {
        super(queryString);
    }

    public From from() {
        append(" FROM ");
        return new From(this.queryString);
    }

    @Override
    protected void addCommaAfterFirstValue() {
        append(", ");
    }
}
