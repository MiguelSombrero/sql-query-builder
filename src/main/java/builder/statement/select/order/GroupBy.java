package builder.statement.select.order;

import builder.query.Query;
import factory.ValidatorFactory;
import validation.Validator;

public class GroupBy {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    private Query query;

    public GroupBy(Query query) {
        this.query = query;
    }

    public Having column(String column) {
        validator.validate(column);
        query.append(column);
        return new Having(query);
    }
}
