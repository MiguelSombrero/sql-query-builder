package builder.statement.create.table.column;

import builder.query.Query;
import factory.ValidatorFactory;
import validation.Validator;

public class FirstColumn {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    private Query query;

    public FirstColumn(Query query) {
        this.query = query;
    }

    public ColumnType column(String column) {
        validator.validate(column);
        query.append(column);
        return new ColumnType(query);
    }
}
