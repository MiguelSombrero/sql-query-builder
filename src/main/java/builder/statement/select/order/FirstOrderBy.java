package builder.statement.select.order;

import builder.query.Query;
import factory.ValidatorFactory;
import validation.Validator;

/**
 * Defines method for appending column(s) in
 * 'SELECT ... ORDER BY column(s)' statement.
 */
public class FirstOrderBy extends Limit {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    public FirstOrderBy(Query query) {
        super(query);
    }

    /**
     * Appends 'column' into query string 'SELECT ... ORDER BY column(s)'.
     *
     * @param column Column name to be appended in the query
     * @return Order class which can be used to append more columns
     * or append ASC / DESC to current column
     */
    public Order column(String column) {
        validator.validate(column);
        query.append(column);
        return new Order(query);
    }
}
