package builder.statement.select.order;

import builder.condition.Condition;
import builder.utils.StringAppender;
import query.SelectQuery;

public class Having extends Orderer {
    private StringAppender stringAppender;

    public Having(SelectQuery query) {
        super(query);
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
        query.append(", ");
        stringAppender.validateAndAppend(column);
        return this;
    }

    /**
     * Appends 'HAVING condition' into query string
     * 'SELECT ... GROUP BY column(s) HAVING condition'.
     *
     * @param condition condition to be appended in the query. Condition
     * is build with factory class `HavingClauseFactory`
     *
     * @return Orderer class which can be used for appending
     * 'ORDER BY' in 'SELECT ... ORDER BY column(s)' statement
     */
    public Orderer having(Condition condition) {
        query.append(" HAVING ");
        query.append(condition.build().toString());
        return new Orderer(query);
    }
}
