package builder.statement.select.table;

public class AliasedTable extends TableTemplate {

    public AliasedTable(StringBuilder builder) {
        super(builder);
    }

    @Override
    protected void addCommaAfterFirstValue() {
        append(", ");
    }
}
