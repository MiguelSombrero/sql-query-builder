package builder.statement.select.table;

import builder.query.Query;
import builder.statement.select.SelectQueryBuilder;
import factory.ValidatorFactory;
import validation.Validator;

public class From {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    private Query query;

    public From(Query query) {
        this.query = query;
    }
    
    public Table table(String table) {
        validator.validate(table);
        query.append(table);
        return new Table(query);
    }

    public SubQuery sub(SelectQueryBuilder subQuery) {
        query.append("(");
        query.append(subQuery.build());
        query.append(")");
        return new SubQuery(query);
    }
}
