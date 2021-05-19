package builder.statement.create.index;

import builder.query.Query;
import builder.TerminalOperation;
import builder.query.QueryAppender;
import factory.ValidatorFactory;
import validation.Validator;

import java.util.Arrays;

public class IndexedColumn {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    private Query query;

    public IndexedColumn(Query query) {
        this.query = query;
    }

    public TerminalOperation columns(String ...listOfColumns) {
        validateList(listOfColumns);
        query.append(" ");
        QueryAppender.appendList(query, listOfColumns);
        return new TerminalOperation(query);
    }

    private void validateList(String[] columns) {
        Arrays.stream(columns).forEach(column -> validator.validate(column));
    }
}
