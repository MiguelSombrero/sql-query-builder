package builder.query.select.order;

import query.Statement;

public class Order extends OrderBy {

    public Order(Statement statement) {
        super(statement);
    }

    /**
     * Appends 'DESC' into query string 'SELECT ... ORDER BY column(s) DESC'.
     *
     * @return OrderBy class which defines method
     * for appending column(s) in
     * 'SELECT ... ORDER BY column(s)' statement
     */
    public OrderBy desc() {
        statement.append(" DESC");
        return new OrderBy(statement);
    }

    /**
     * Appends 'ASC' into query string 'SELECT ... ORDER BY column(s) ASC'.
     *
     * @return OrderBy class which defines method
     * for appending column(s) in
     * 'SELECT ... ORDER BY column(s)' statement
     */
    public OrderBy asc() {
        statement.append(" ASC");
        return new OrderBy(statement);
    }
}
