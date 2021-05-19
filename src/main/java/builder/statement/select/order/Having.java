package builder.statement.select.order;

import builder.query.Query;
import builder.condition.Condition;
import factory.ValidatorFactory;
import validation.Validator;

public class Having extends Orderer {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    public Having(Query query) {
        super(query);
    }

    public Having column(String column) {
        validator.validate(column);
        query.append(", ");
        query.append(column);
        return this;
    }

    public Orderer having(Condition havingClause) {
        query.append(" HAVING ");
        query.append(havingClause.build());
        return new Orderer(query);
    }
}
