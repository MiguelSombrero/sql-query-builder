package builder.statement.select.table;

import builder.SQLStringAppender;
import factory.ValidatorFactory;
import validation.Validator;

public class AliasedOn extends SQLStringAppender {
    protected static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    public AliasedOn(StringBuilder queryString) {
        super(queryString);
    }

    public JoinTable on(String column, String joinColumn) {
        validator.validate(column);
        validator.validate(joinColumn);
        append(" ON ");
        append(column);
        append(" = ");
        append(joinColumn);
        return new JoinTable(this.queryString);
    }
}
