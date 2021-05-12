package builder.statement.select.order;

public class Order extends OrderBy {

    public Order(StringBuilder queryString) {
        super(queryString);
    }

    public OrderBy desc() {
        append(" DESC");
        return new OrderBy(this.queryString);
    }

    public OrderBy asc() {
        append(" ASC");
        return new OrderBy(this.queryString);
    }
}
