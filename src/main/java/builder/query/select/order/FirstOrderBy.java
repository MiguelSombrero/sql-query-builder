package builder.query.select.order;

import builder.appender.StringAppender;
import query.SelectQuery;

public class FirstOrderBy extends Limit {

    public FirstOrderBy(SelectQuery query) {
        super(query);
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
        StringAppender.validateAndAppend(query, column);
        return new Order(query);
    }
}
