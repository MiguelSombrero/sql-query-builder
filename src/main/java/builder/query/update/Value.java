package builder.query.update;

import builder.appender.ValueAppender;
import query.UpdateQuery;

public class Value {
    private UpdateQuery query;

    public Value(UpdateQuery query) {
        this.query = query;
    }

    /**
     * Validates user input and appends string 'value' into
     * 'UPDATE table SET column = value, ...' statement.
     *
     * @param value String value to be appended in selected column
     *
     * @return Column class which can be used to append more
     * columns into UPDATE statement, proceed to WHERE
     * clause or terminate query building
     */
    public Column setString(String value) {
        ValueAppender.appendStringParam(query, value);
        return new Column(query);
    }

    /**
     * Validates user input and appends integer 'value' into
     * 'UPDATE table SET column = value, ...' statement.
     *
     * @param value Int value to be appended in selected column
     *
     * @return Column class which can be used to append more
     * columns into UPDATE statement, proceed to WHERE
     * clause or terminate query building
     */
    public Column setInt(int value) {
        ValueAppender.appendIntParam(query, value);
        return new Column(query);
    }

    /**
     * Validates user input and appends integer 'value' into
     * 'UPDATE table SET column = value, ...' statement.
     *
     * @param value Long value to be appended in selected column
     *
     * @return Column class which can be used to append more
     * columns into UPDATE statement, proceed to WHERE
     * clause or terminate query building
     */
    public Column setLong(long value) {
        ValueAppender.appendLongParam(query, value);
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
    public Column setDouble(double value) {
        ValueAppender.appendDoubleParam(query, value);
        return new Column(query);
    }

    /**
     * Validates user input and appends integer 'value' into
     * 'UPDATE table SET column = value, ...' statement.
     *
     * @param value Date value to be appended in selected column
     *
     * @return Column class which can be used to append more
     * columns into UPDATE statement, proceed to WHERE
     * clause or terminate query building
     */
    public Column setDate(String value) {
        ValueAppender.appendDateParam(query, value);
        return new Column(query);
    }

    /**
     * Validates user input and appends integer 'value' into
     * 'UPDATE table SET column = value, ...' statement.
     *
     * @param value DateTime value to be appended in selected column
     *
     * @return Column class which can be used to append more
     * columns into UPDATE statement, proceed to WHERE
     * clause or terminate query building
     */
    public Column setTimestamp(String value) {
        ValueAppender.appendTimestampParam(query, value);
        return new Column(query);
    }

    /**
     * Validates user input and appends integer 'value' into
     * 'UPDATE table SET column = value, ...' statement.
     *
     * @param value Boolean value to be appended in selected column
     *
     * @return Column class which can be used to append more
     * columns into UPDATE statement, proceed to WHERE
     * clause or terminate query building
     */
    public Column setBoolean(boolean value) {
        ValueAppender.appendBooleanParam(query, value);
        return new Column(query);
    }

    /**
     * Validates user input and appends integer 'value' into
     * 'UPDATE table SET column = value, ...' statement.
     *
     * @param value ByteArray value to be appended in selected column
     *
     * @return Column class which can be used to append more
     * columns into UPDATE statement, proceed to WHERE
     * clause or terminate query building
     */
    public Column setByteArray(byte[] value) {
        ValueAppender.appendByteArrayParam(query, value);
        return new Column(query);
    }
}
