package builder.statement.update;

import builder.Query;
import builder.utils.StringValueAppender;

public class Value {
    private StringValueAppender stringValueAppender;
    private Query query;

    public Value(Query query) {
        this.query = query;
        this.stringValueAppender = new StringValueAppender(query);
    }

    /**
     * Appends 'value' into 'UPDATE table SET column = value, ...' statement.
     *
     * @param value Value to be appended in selected column
     *
     * @return Column class which can be used to append more
     * columns into UPDATE statement, proceed to WHERE
     * clause or terminate query building
     */
    public Column value(String value) {
        stringValueAppender.validateAndAppend(value);
        return new Column(query);
    }

    /**
     * Appends 'value' into 'UPDATE table SET column = value, ...' statement.
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
     * Appends 'value' into 'UPDATE table SET column = value, ...' statement.
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
