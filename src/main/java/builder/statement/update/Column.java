package builder.statement.update;

import builder.TerminalOperation;
import builder.condition.Condition;
import factory.ValidatorFactory;
import validation.Validator;

public class Column extends TerminalOperation {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    public Column(StringBuilder queryString) {
        super(queryString);
    }

    public Value column(String column) {
        validator.validate(column);
        append(", ");
        append(column);
        append(" = ");
        return new Value(this.queryString);
    }

    public TerminalOperation where(Condition clause) {
        append(" WHERE ");
        append(clause.build());
        return new TerminalOperation(this.queryString);
    }
}
