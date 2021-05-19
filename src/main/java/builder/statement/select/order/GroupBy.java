package builder.statement.select.order;

import builder.query.Query;
import builder.TerminalOperation;
import factory.ValidatorFactory;
import validation.Validator;

public class GroupBy extends TerminalOperation {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    public GroupBy(Query query) {
        super(query);
    }

    public Having column(String column) {
        validator.validate(column);
        query.append(column);
        return new Having(query);
    }
}
