package builder.query.select.order;

import builder.clause.Condition;
import builder.appender.StringAppender;
import query.Clause;

public class Having extends Orderer {

    public Having(Clause clause) {
        super(clause);
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
        clause.append(", ");
        StringAppender.validateAndAppend(clause, column);
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
        clause.append(" HAVING ");
        clause.mergeClause(condition.build());
        return new Orderer(clause);
    }
}
