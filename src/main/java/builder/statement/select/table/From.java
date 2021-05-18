package builder.statement.select.table;

import builder.SQLStringAppender;
import builder.statement.select.SelectBuilder;
import factory.ValidatorFactory;
import validation.Validator;

public class From extends SQLStringAppender {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    public From(StringBuilder queryString) {
        super(queryString);
    }

    public Table table(String table) {
        validator.validate(table);
        append(table);
        return new Table(this.queryString);
    }

    public SubQuery sub(SelectBuilder query) {
        append("(");
        append(query.build());
        append(")");
        return new SubQuery(this.queryString);
    }
}
