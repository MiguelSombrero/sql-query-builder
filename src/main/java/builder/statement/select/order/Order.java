package builder.statement.select.order;

public class Order extends OrderBy {
    private OrderBy parent;

    public Order(OrderBy parent, StringBuilder builder) {
        this.parent = parent;
        this.builder = builder;
    }

    public OrderBy desc() {
        append(" DESC");
        return parent;
    }

    public OrderBy asc() {
        append(" ASC");
        return parent;
    }
}
