package builder.query.select.order;

import query.Clause;

public class Order extends OrderBy {

    public Order(Clause clause) {
        super(clause);
    }

    /**
     * Appends 'DESC' into query string 'SELECT ... ORDER BY column(s) DESC'.
     *
     * @return OrderBy class which defines method
     * for appending column(s) in
     * 'SELECT ... ORDER BY column(s)' statement
     */
    public OrderBy desc() {
        clause.append(" DESC");
        return new OrderBy(clause);
    }

    /**
     * Appends 'ASC' into query string 'SELECT ... ORDER BY column(s) ASC'.
     *
     * @return OrderBy class which defines method
     * for appending column(s) in
     * 'SELECT ... ORDER BY column(s)' statement
     */
    public OrderBy asc() {
        clause.append(" ASC");
        return new OrderBy(clause);
    }
}
