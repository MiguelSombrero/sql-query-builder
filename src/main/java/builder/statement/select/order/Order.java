package builder.statement.select.order;

public class Order extends OrderBy {

    public Order(StringBuilder builder) {
        super(builder);
    }

    public OrderBy desc() {
        append(" DESC");
        return new OrderBy(this.builder);
    }

    public OrderBy asc() {
        append(" ASC");
        return new OrderBy(this.builder);
    }
}
