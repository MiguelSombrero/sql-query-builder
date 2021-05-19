package builder.statement.select.order;

import builder.query.Query;
import factory.ValidatorFactory;
import validation.Validator;

public class FirstOrderBy extends Limit {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    public FirstOrderBy(Query query) {
        super(query);
    }

    public Order column(String column) {
        validator.validate(column);
        query.append(column);
        return new Order(query);
    }
}
