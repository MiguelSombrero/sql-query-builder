package builder.statement.insert;

import builder.Query;
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
        query.appendList(listOfColumns);
        query.append(" ");
        return new Column(query);
    }

    private void validateList(String[] columns) {
        Arrays.stream(columns).forEach(column -> validator.validate(column));
    }
}
