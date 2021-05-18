package builder.statement.create.table.foreignkey;

import builder.SQLStringAppender;
import factory.ValidatorFactory;
import validation.Validator;

public class Reference extends SQLStringAppender {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    public Reference(StringBuilder queryString) {
        super(queryString);
    }

    public OnAction references(String column, String ofTable) {
        validator.validate(column);
        validator.validate(ofTable);
        append(" REFERENCES ");
        append(ofTable);
        append("(");
        append(column);
        append(")");
        return new OnAction(this.queryString);
    }
}
