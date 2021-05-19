package builder.statement.select.table;

import builder.Query;
import factory.ValidatorFactory;
import validation.Validator;

public class AliasedOn {
    protected static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    protected Query query;

    public AliasedOn(Query query) {
        this.query = query;
    }

    public JoinTable on(String column, String joinColumn) {
        validator.validate(column);
        validator.validate(joinColumn);
        query.append(" ON ");
        query.append(column);
        query.append(" = ");
        query.append(joinColumn);
        return new JoinTable(query);
    }
}
