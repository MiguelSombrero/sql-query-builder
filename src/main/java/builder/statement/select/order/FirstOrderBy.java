package builder.statement.select.order;

import builder.Query;
import builder.utils.StringAppender;

public class FirstOrderBy extends Limit {
    private StringAppender stringAppender;

    public FirstOrderBy(Query query) {
        super(query);
        this.stringAppender = new StringAppender(query);
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
        stringAppender.validateAndAppend(column);
        return new Order(query);
    }
}
