package builder.statement.select.order;

import builder.Query;
import factory.ValidatorFactory;
import validation.Validator;

public class OrderBy extends Limit {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    public OrderBy(Query query) {
        super(query);
    }

    public Order column(String column) {
        validator.validate(column);
        query.append(", ");
        query.append(column);
        return new Order(query);
    }
}
