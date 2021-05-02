package builder.statement.select.order;

public class OrderBy extends Limit {
    private boolean isFirst;

    public OrderBy() {
    }

    public OrderBy(StringBuilder builder) {
        super(builder);
        this.isFirst = true;
    }

    public Order column(String columnName) {
        addCommaAfterFirstValue();
        append(columnName);
        return new Order(this, this.builder);
    }

    protected void addCommaAfterFirstValue() {
        if (!isFirst) {
            append(", ");
        }

        this.isFirst = false;
    }
}
