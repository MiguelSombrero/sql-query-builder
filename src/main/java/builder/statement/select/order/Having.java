package builder.statement.select.order;

import builder.query.Query;
import builder.condition.Condition;
import factory.ValidatorFactory;
import validation.Validator;

/**
 * Defines method for appending column(s) in
 * 'SELECT ... GROUP BY column(s)' statement.
 */
public class Having extends Orderer {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    public Having(Query query) {
        super(query);
    }

    /**
     * Appends 'column' into query string 'SELECT ... GROUP BY column(s)'.
     *
     * @param column Column name to be appended in the query
     * @return Having class which can be used to append more columns
     * or defining HAVING condition to GROUP BY clause
     */
    public Having column(String column) {
        validator.validate(column);
        query.append(", ");
        query.append(column);
        return this;
    }

    /**
     * Appends 'HAVING condition' into query string
     * 'SELECT ... GROUP BY column(s) HAVING condition'.
     *
     * @param condition condition to be appended in the query. Condition
     * is build with factory class `HavingClauseFactory`
     * @return Orderer class which can be used for appending
     * 'ORDER BY' in 'SELECT ... ORDER BY column(s)' statement
     */
    public Orderer having(Condition condition) {
        query.append(" HAVING ");
        query.append(condition.build());
        return new Orderer(query);
    }
}
