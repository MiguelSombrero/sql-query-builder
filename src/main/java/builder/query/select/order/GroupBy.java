package builder.query.select.order;

import builder.appender.StringAppender;
import query.Clause;

public class GroupBy {
    private Clause query;

    public GroupBy(Clause clause) {
        this.query = clause;
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
        StringAppender.validateAndAppend(query, column);
        return new Having(query);
    }
}
