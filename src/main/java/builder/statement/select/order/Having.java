package builder.statement.select.order;

import builder.condition.Condition;
import factory.ValidatorFactory;
import validation.Validator;

public class Having extends Orderer {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    public Having(StringBuilder queryString) {
        super(queryString);
    }

    public Having column(String column) {
        validator.validate(column);
        append(", ");
        append(column);
        return this;
    }

    public Orderer having(Condition havingClause) {
        append(" HAVING ");
        append(havingClause.build());
        return new Orderer(this.queryString);
    }
}
