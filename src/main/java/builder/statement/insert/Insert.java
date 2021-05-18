package builder.statement.insert;

import factory.ValidatorFactory;
import validation.Validator;

import java.util.Arrays;

public class Insert extends Column {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    public Insert(StringBuilder queryString) {
        super(queryString);
    }

    public Column columns(String ...listOfColumns) {
        validateList(listOfColumns);
        appendList(listOfColumns);
        append(" ");
        return new Column(this.queryString);
    }

    private void validateList(String[] columns) {
        Arrays.stream(columns).forEach(column -> validator.validate(column));
    }
}
