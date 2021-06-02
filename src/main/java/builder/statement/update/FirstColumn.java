package builder.statement.update;

import query.dml.DMLQuery;
import builder.utils.StringAppender;

public class FirstColumn {
    private StringAppender stringAppender;

    private DMLQuery query;

    public FirstColumn(DMLQuery query) {
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
