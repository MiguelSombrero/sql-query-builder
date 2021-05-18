package builder.statement.create.index;

import builder.SQLStringAppender;
import builder.TerminalOperation;
import factory.ValidatorFactory;
import validation.Validator;

import java.util.Arrays;
import java.util.List;

public class IndexedColumn extends SQLStringAppender {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    public IndexedColumn(StringBuilder queryString) {
        super(queryString);
    }

    public TerminalOperation columns(String ...listOfColumns) {
        validateList(listOfColumns);
        append(" ");
        appendList(listOfColumns);
        return new TerminalOperation(this.queryString);
    }

    private void validateList(String[] columns) {
        Arrays.stream(columns).forEach(column -> validator.validate(column));
    }
}
