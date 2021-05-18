package builder.statement.select.order;

import factory.ValidatorFactory;
import validation.Validator;

public class FirstOrderBy extends Limit {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    public FirstOrderBy(StringBuilder queryString) {
        super(queryString);
    }

    public Order column(String column) {
        validator.validate(column);
        append(column);
        return new Order(this.queryString);
    }
}
