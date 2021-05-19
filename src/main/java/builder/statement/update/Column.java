package builder.statement.update;

import builder.Query;
import builder.TerminalOperation;
import builder.condition.Condition;
import factory.ValidatorFactory;
import validation.Validator;

public class Column extends TerminalOperation {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    public Column(Query query) {
        super(query);
    }

    public Value column(String column) {
        validator.validate(column);
        query.append(", ");
        query.append(column);
        query.append(" = ");
        return new Value(query);
    }

    public TerminalOperation where(Condition clause) {
        query.append(" WHERE ");
        query.append(clause.build());
        return new TerminalOperation(query);
    }
}
