package builder.statement.update;

import builder.appender.StringAppender;
import query.dml.UpdateQuery;

public class FirstColumn {
    private UpdateQuery query;

    public FirstColumn(UpdateQuery query) {
        this.query = query;
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
        StringAppender.validateAndAppend(query, column);
        query.append(" = ");
        return new Value(query);
    }
}
