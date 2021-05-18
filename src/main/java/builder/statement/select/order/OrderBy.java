package builder.statement.select.order;

import factory.ValidatorFactory;
import validation.Validator;

public class OrderBy extends Limit {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    public OrderBy(StringBuilder queryString) {
        super(queryString);
    }

    public Order column(String column) {
        validator.validate(column);
        append(", ");
        append(column);
        return new Order(this.queryString);
    }
}
