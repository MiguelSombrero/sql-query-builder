package builder.statement.create.table.foreignkey;

import builder.Query;
import factory.ValidatorFactory;
import validation.Validator;

public class Reference {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    private Query query;

    public Reference(Query query) {
        this.query = query;
    }

    public OnAction references(String column, String ofTable) {
        validator.validate(column);
        validator.validate(ofTable);
        query.append(" REFERENCES ");
        query.append(ofTable);
        query.append("(");
        query.append(column);
        query.append(")");
        return new OnAction(query);
    }
}
