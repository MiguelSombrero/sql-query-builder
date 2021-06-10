package builder.statement.update;

import builder.appender.ValueAppender;
import query.dml.UpdateQuery;

public class Value {
    private static ValueAppender valueAppender;
    private UpdateQuery query;

    public Value(UpdateQuery query) {
        this.query = query;
    }

    /**
     * Validates user input and appends string 'value' into
     * 'UPDATE table SET column = value, ...' statement.
     *
     * @param value Value to be appended in selected column
     *
     * @return Column class which can be used to append more
     * columns into UPDATE statement, proceed to WHERE
     * clause or terminate query building
     */
    public Column value(String value) {
        valueAppender.validateAndAppendString(query, value);
        return new Column(query);
    }

    /**
     * Validates user input and appends integer 'value' into
     * 'UPDATE table SET column = value, ...' statement.
     *
     * @param value Value to be appended in selected column
     *
     * @return Column class which can be used to append more
     * columns into UPDATE statement, proceed to WHERE
     * clause or terminate query building
     */
    public Column value(int value) {
        query.append(value);
        return new Column(query);
    }

    /**
     * Validates user input and appends double 'value' into
     * 'UPDATE table SET column = value, ...' statement.
     *
     * @param value Value to be appended in selected column
     *
     * @return Column class which can be used to append more
     * columns into UPDATE statement, proceed to WHERE
     * clause or terminate query building
     */
    public Column value(double value) {
        query.append(value);
        return new Column(query);
    }
}
