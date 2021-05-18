package builder.statement.create.table.foreignkey;

import builder.statement.create.TerminalCreateOperation;
import factory.ValidatorFactory;
import validation.Validator;

public class ForeignKey extends TerminalCreateOperation {
    protected static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    public ForeignKey(StringBuilder queryString) {
        super(queryString);
    }

    public Reference foreignKey(String column) {
        validator.validate(column);
        append(", FOREIGN KEY (");
        append(column);
        append(")");
        return new Reference(this.queryString);
    }
}
