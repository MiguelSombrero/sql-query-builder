package builder.statement.select.order;

import builder.Query;
import builder.utils.StringAppender;

public class GroupBy {
    private StringAppender stringAppender;

    private Query query;

    public GroupBy(Query query) {
        this.query = query;
        this.stringAppender = new StringAppender(query);
    }

    /**
     * Validates user input and appends 'column' into
     * query string 'SELECT ... GROUP BY column(s)'.
     *
     * @param column Column name to be appended in the query
     *
     * @return Having class which can be used to append more columns
     * or defining HAVING condition to GROUP BY clause
     */
    public Having column(String column) {
        stringAppender.validateAndAppend(column);
        return new Having(query);
    }
}
