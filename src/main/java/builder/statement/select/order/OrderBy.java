package builder.statement.select.order;

public class OrderBy extends Limit {

    public OrderBy(StringBuilder builder) {
        super(builder);
    }

    public Order column(String columnName) {
        append(", ");
        append(columnName);
        return new Order(this.builder);
    }
}
