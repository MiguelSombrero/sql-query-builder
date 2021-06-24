package builder.query.select.order;

import builder.appender.StringAppender;
import query.Clause;

public class OrderBy extends Limit {

    public OrderBy(Clause clause) {
        super(clause);
    }

    /**
     * Validates user input and appends 'column' into
     * query string 'SELECT ... ORDER BY column(s)'.
     *
     * @param column Column name to be appended in the query
     *
     * @return Order class which can be used to append more columns
     * or append ASC / DESC to current column
     */
    public Order column(String column) {
        clause.append(", ");
        StringAppender.validateAndAppend(clause, column);
        return new Order(clause);
    }
}
