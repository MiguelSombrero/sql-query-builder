package builder.statement.select.order;

import builder.query.Query;

public class Order extends OrderBy {

    public Order(Query query) {
        super(query);
    }

    public OrderBy desc() {
        query.append(" DESC");
        return new OrderBy(query);
    }

    public OrderBy asc() {
        query.append(" ASC");
        return new OrderBy(query);
    }
}
