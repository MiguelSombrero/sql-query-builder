package builder.statement.select.order;

import builder.Query;
import factory.ValidatorFactory;
import validation.Validator;

public class GroupBy {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    private Query query;

    public GroupBy(Query query) {
        this.query = query;
    }

    /**
     * Appends 'column' into query string 'SELECT ... GROUP BY column(s)'.
     *
     * @param column Column name to be appended in the query
     *
     * @return Having class which can be used to append more columns
     * or defining HAVING condition to GROUP BY clause
     */
    public Having column(String column) {
        validator.validate(column);
        query.append(column);
        return new Having(query);
    }
}
