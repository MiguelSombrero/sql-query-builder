package builder.statement.update;

import builder.Query;
import builder.utils.StringAppender;
import factory.ValidatorFactory;
import validation.Validator;

public class Table {
    private StringAppender stringAppender;

    private Query query;

    public Table(Query query) {
        this.query = query;
        this.stringAppender = new StringAppender(query);
    }

    /**
     * Appends 'table SET' into 'UPDATE table SET column = ...' statements.
     *
     * @param table Table name to be appended
     *
     * @return FirstColumn class which can be used to append
     * columns into UPDATE statement
     */
    public FirstColumn table(String table) {
        stringAppender.validateAndAppend(table);
        query.append(" SET ");
        return new FirstColumn(query);
    }
}
