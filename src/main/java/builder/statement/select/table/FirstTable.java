package builder.statement.select.table;

public class FirstTable extends TableTemplate {

    public FirstTable(StringBuilder builder) {
        super(builder);
    }

    @Override
    protected void addCommaAfterFirstValue() {
    }
}
