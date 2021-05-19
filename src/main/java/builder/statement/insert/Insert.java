package builder.statement.insert;

import builder.query.Query;
import builder.query.QueryAppender;
import factory.ValidatorFactory;
import validation.Validator;

import java.util.Arrays;

public class Insert extends Column {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    public Insert(Query query) {
        super(query);
    }

    public Column columns(String ...listOfColumns) {
        validateList(listOfColumns);
        QueryAppender.appendList(query, listOfColumns);
        query.append(" ");
        return new Column(query);
    }

    private void validateList(String[] columns) {
        Arrays.stream(columns).forEach(column -> validator.validate(column));
    }
}
