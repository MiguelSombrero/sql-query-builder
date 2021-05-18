package builder.statement.create.index;

import builder.SQLStringAppender;
import factory.ValidatorFactory;
import validation.Validator;

public class Index extends SQLStringAppender {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    public Index(StringBuilder queryString) {
        super(queryString);
    }

    public IndexedColumn on(String table) {
        validator.validate(table);
        append(" ON ");
        append(table);
        return new IndexedColumn(this.queryString);
    }
}
