package builder.statement.update;

import utils.StringAppender;
import query.dml.UpdateQuery;

public class FirstColumn {
    private StringAppender stringAppender;

    private UpdateQuery query;

    public FirstColumn(UpdateQuery query) {
        this.query = query;
        this.stringAppender = new StringAppender(query);
    }

    /**
     * Validates user input and appends 'column1 =' into
     * 'UPDATE table SET column1 = value1, ...' statement.
     *
     * @param column Column name to be appended into UPDATE query
     *
     * @return Value class which is used to append value
     * into selected column
     */
    public Value column(String column) {
        stringAppender.validateAndAppend(column);
        query.append(" = ");
        return new Value(query);
    }
}
