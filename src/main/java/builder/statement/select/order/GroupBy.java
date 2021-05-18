package builder.statement.select.order;

import builder.TerminalOperation;
import factory.ValidatorFactory;
import validation.Validator;

public class GroupBy extends TerminalOperation {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    public GroupBy(StringBuilder queryString) {
        super(queryString);
    }

    public Having column(String column) {
        validator.validate(column);
        append(column);
        return new Having(this.queryString);
    }
}
