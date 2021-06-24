package builder.query.update;

import builder.appender.ValueAppender;
import query.Clause;

import java.math.BigDecimal;

public class Value {
    private Clause clause;

    public Value(Clause clause) {
        this.clause = clause;
    }

    /**
     * Validates user input and appends string 'value' into
     * 'UPDATE table SET column = value, ...' statement.
     *
     * SQL types CHAR, NCHAR, LONGNVARCHAR, LONGVARCHAR,
     * VARCHAR and NVARCHAR maps to String. Use this method
     * for setting these values in query.
     *
     * @param value String value to be appended in selected column
     *
     * @return Column class which can be used to append more
     * columns into UPDATE statement, proceed to WHERE
     * clause or terminate query building
     */
    public Column setString(String value) {
        ValueAppender.appendStringParam(clause, value);
        return new Column(clause);
    }

    /**
     * Validates user input and appends integer 'value' into
     * 'UPDATE table SET column = value, ...' statement.
     *
     * SQL type INTEGER maps to Integer. Use this method
     * for setting these values in query.
     *
     * @param value Int value to be appended in selected column
     *
     * @return Column class which can be used to append more
     * columns into UPDATE statement, proceed to WHERE
     * clause or terminate query building
     */
    public Column setInt(int value) {
        ValueAppender.appendIntParam(clause, value);
        return new Column(clause);
    }

    /**
     * Validates user input and appends integer 'value' into
     * 'UPDATE table SET column = value, ...' statement.
     *
     * SQL type SMALLINT maps to Short. Use this method
     * for setting these values in query.
     *
     * @param value Short value to be appended in selected column
     *
     * @return Column class which can be used to append more
     * columns into UPDATE statement, proceed to WHERE
     * clause or terminate query building
     */
    public Column setShort(short value) {
        ValueAppender.appendShortParam(clause, value);
        return new Column(clause);
    }

    /**
     * Validates user input and appends integer 'value' into
     * 'UPDATE table SET column = value, ...' statement.
     *
     * SQL type BIGINT maps to Long. Use this method
     * for setting these values in query.
     *
     * @param value Long value to be appended in selected column
     *
     * @return Column class which can be used to append more
     * columns into UPDATE statement, proceed to WHERE
     * clause or terminate query building
     */
    public Column setLong(long value) {
        ValueAppender.appendLongParam(clause, value);
        return new Column(clause);
    }

    /**
     * Validates user input and appends double 'value' into
     * 'UPDATE table SET column = value, ...' statement.
     *
     * SQL type DOUBLE and FLOAT maps to Double. Use this method
     * for setting these values in query.
     *
     * @param value Value to be appended in selected column
     *
     * @return Column class which can be used to append more
     * columns into UPDATE statement, proceed to WHERE
     * clause or terminate query building
     */
    public Column setDouble(double value) {
        ValueAppender.appendDoubleParam(clause, value);
        return new Column(clause);
    }

    /**
     * Validates user input and appends double 'value' into
     * 'UPDATE table SET column = value, ...' statement.
     *
     * SQL types DECIMAL and NUMERIC maps to BigDecimal.
     * Use this method for setting these values in query.
     *
     * @param value Value to be appended in selected column
     *
     * @return Column class which can be used to append more
     * columns into UPDATE statement, proceed to WHERE
     * clause or terminate query building
     */
    public Column setBigDecimal(BigDecimal value) {
        ValueAppender.appendBigDecimalParam(clause, value);
        return new Column(clause);
    }

    /**
     * Validates user input and appends integer 'value' into
     * 'UPDATE table SET column = value, ...' statement.
     *
     * SQL type DATE maps to Date. Use this method for
     * setting these values in query.
     *
     * @param value Date value to be appended in selected column
     *
     * @return Column class which can be used to append more
     * columns into UPDATE statement, proceed to WHERE
     * clause or terminate query building
     */
    public Column setDate(String value) {
        ValueAppender.appendDateParam(clause, value);
        return new Column(clause);
    }

    /**
     * Validates user input and appends integer 'value' into
     * 'UPDATE table SET column = value, ...' statement.
     *
     * SQL type TIMESTAMP maps to Timestamp. Use this method for
     * setting these values in query.
     *
     * @param value DateTime value to be appended in selected column
     *
     * @return Column class which can be used to append more
     * columns into UPDATE statement, proceed to WHERE
     * clause or terminate query building
     */
    public Column setTimestamp(String value) {
        ValueAppender.appendTimestampParam(clause, value);
        return new Column(clause);
    }

    /**
     * Validates user input and appends integer 'value' into
     * 'UPDATE table SET column = value, ...' statement.
     *
     * SQL types BOOLEAN, TINYINT and BIT maps to Timestamp.
     * Use this method for setting these values in query.
     *
     * @param value Boolean value to be appended in selected column
     *
     * @return Column class which can be used to append more
     * columns into UPDATE statement, proceed to WHERE
     * clause or terminate query building
     */
    public Column setBoolean(boolean value) {
        ValueAppender.appendBooleanParam(clause, value);
        return new Column(clause);
    }

    /**
     * Validates user input and appends integer 'value' into
     * 'UPDATE table SET column = value, ...' statement.
     *
     * SQL types BLOB, BINARY, VARBINARY and LONGVARBINARY
     * maps to Timestamp. Use this method for setting these
     * values in query.
     *
     * @param value ByteArray value to be appended in selected column
     *
     * @return Column class which can be used to append more
     * columns into UPDATE statement, proceed to WHERE
     * clause or terminate query building
     */
    public Column setByteArray(byte[] value) {
        ValueAppender.appendByteArrayParam(clause, value);
        return new Column(clause);
    }
}
