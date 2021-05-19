package builder.statement.create.table.foreignkey;

import builder.query.Query;
import builder.statement.create.TerminalCreateOperation;
import factory.ValidatorFactory;
import validation.Validator;

public class ForeignKey extends TerminalCreateOperation {
    protected static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    public ForeignKey(Query query) {
        super(query);
    }

    public Reference foreignKey(String column) {
        validator.validate(column);
        query.append(", FOREIGN KEY (");
        query.append(column);
        query.append(")");
        return new Reference(query);
    }
}
