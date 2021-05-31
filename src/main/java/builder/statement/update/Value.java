package builder.statement.update;

import query.SQLQuery;
import builder.utils.StringValueAppender;

public class Value {
    private StringValueAppender stringValueAppender;
    private SQLQuery SQLQuery;

    public Value(SQLQuery SQLQuery) {
        this.SQLQuery = SQLQuery;
        this.stringValueAppender = new StringValueAppender(SQLQuery);
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
        stringValueAppender.validateAndAppendStringValue(value);
        return new Column(SQLQuery);
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
        SQLQuery.append(value);
        return new Column(SQLQuery);
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
        SQLQuery.append(value);
        return new Column(SQLQuery);
    }
}
