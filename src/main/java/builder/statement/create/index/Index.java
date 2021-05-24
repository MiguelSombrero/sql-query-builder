package builder.statement.create.index;

import builder.Query;
import factory.ValidatorFactory;
import validation.Validator;

public class Index {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    private Query query;

    public Index(Query query) {
        this.query = query;
    }

    public IndexedColumn on(String table) {
        validator.validate(table);
        query.append(" ON ");
        query.append(table);
        return new IndexedColumn(query);
    }
}
