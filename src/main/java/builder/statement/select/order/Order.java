package builder.statement.select.order;

import query.dql.DQLQuery;

public class Order extends OrderBy {

    public Order(DQLQuery query) {
        super(query);
    }

    /**
     * Appends 'DESC' into query string 'SELECT ... ORDER BY column(s) DESC'.
     *
     * @return OrderBy class which defines method
     * for appending column(s) in
     * 'SELECT ... ORDER BY column(s)' statement
     */
    public OrderBy desc() {
        query.append(" DESC");
        return new OrderBy(query);
    }

    /**
     * Appends 'ASC' into query string 'SELECT ... ORDER BY column(s) ASC'.
     *
     * @return OrderBy class which defines method
     * for appending column(s) in
     * 'SELECT ... ORDER BY column(s)' statement
     */
    public OrderBy asc() {
        query.append(" ASC");
        return new OrderBy(query);
    }
}
