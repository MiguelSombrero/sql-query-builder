package builder.query.insert;

import builder.appender.ValueAppender;
import query.Statement;

import java.math.BigDecimal;

public abstract class ValueTemplate extends TerminalClosingInsertOperation {

    public ValueTemplate(Statement statement) {
        super(statement);
    }

    /**
     * Validates user input and appends string 'value(s)'
     * into 'INSERT INTO table VALUE (value(s))' statement.
     *
     * SQL types CHAR, NCHAR, LONGNVARCHAR, LONGVARCHAR,
     * VARCHAR and NVARCHAR maps to String. Use this method
     * for setting these values in query.
     *
     * @param value String value to be appended
     *
     * @return Value class which can be used to append
     * value (with comma in front) into 'VALUE (value(s))'
     * statement
     */
    public Value setString(String value) {
        addCommaAfterFirstValue();
        ValueAppender.appendStringParam(statement, value);
        return new Value(statement);
    }

    /**
     * Validates user input and appends integer 'value(s)'
     * into 'INSERT INTO table VALUE (value(s))' statement.
     *
     * SQL type INTEGER maps to Integer. Use this method
     * for setting these values in query.
     *
     * @param value Int value to be appended
     *
     * @return Value class which can be used to append
     * value (with comma in front) into 'VALUE (value(s))'
     * statement
     */
    public Value setInt(int value) {
        addCommaAfterFirstValue();
        ValueAppender.appendIntParam(statement, value);
        return new Value(statement);
    }

    /**
     * Validates user input and appends integer 'value(s)'
     * into 'INSERT INTO table VALUE (value(s))' statement.
     *
     * SQL type SMALLINT maps to Short. Use this method
     * for setting these values in query.
     *
     * @param value Short value to be appended
     *
     * @return Value class which can be used to append
     * value (with comma in front) into 'VALUE (value(s))'
     * statement
     */
    public Value setShort(short value) {
        addCommaAfterFirstValue();
        ValueAppender.appendShortParam(statement, value);
        return new Value(statement);
    }

    /**
     * Validates user input and appends integer 'value(s)'
     * into 'INSERT INTO table VALUE (value(s))' statement.
     *
     * SQL type BIGINT maps to Long. Use this method
     * for setting these values in query.
     *
     * @param value Long value to be appended
     *
     * @return Value class which can be used to append
     * value (with comma in front) into 'VALUE (value(s))'
     * statement
     */
    public Value setLong(long value) {
        addCommaAfterFirstValue();
        ValueAppender.appendLongParam(statement, value);
        return new Value(statement);
    }

    /**
     * Validates user input and appends double 'value(s)'
     * into 'INSERT INTO table VALUE (value(s))' statement.
     *
     * SQL type DOUBLE and FLOAT maps to Double. Use this method
     * for setting these values in query.
     *
     * @param value Double value to be appended
     *
     * @return Value class which can be used to append
     * value (with comma in front) into 'VALUE (value(s))'
     * statement
     */
    public Value setDouble(double value) {
        addCommaAfterFirstValue();
        ValueAppender.appendDoubleParam(statement, value);
        return new Value(statement);
    }

    /**
     * Validates user input and appends double 'value(s)'
     * into 'INSERT INTO table VALUE (value(s))' statement.
     *
     * SQL types DECIMAL and NUMERIC maps to BigDecimal.
     * Use this method for setting these values in query.
     *
     * @param value BigDecimal value to be appended
     *
     * @return Value class which can be used to append
     * value (with comma in front) into 'VALUE (value(s))'
     * statement
     */
    public Value setBigDecimal(BigDecimal value) {
        addCommaAfterFirstValue();
        ValueAppender.appendBigDecimalParam(statement, value);
        return new Value(statement);
    }

    /**
     * Validates user input and appends integer 'value(s)'
     * into 'INSERT INTO table VALUE (value(s))' statement.
     *
     * SQL type DATE maps to Date. Use this method for
     * setting these values in query.
     *
     * @param value Date value to be appended
     *
     * @return Value class which can be used to append
     * value (with comma in front) into 'VALUE (value(s))'
     * statement
     */
    public Value setDate(String value) {
        addCommaAfterFirstValue();
        ValueAppender.appendDateParam(statement, value);
        return new Value(statement);
    }

    /**
     * Validates user input and appends integer 'value(s)'
     * into 'INSERT INTO table VALUE (value(s))' statement.
     *
     * SQL type TIME maps to Time. Use this method for
     * setting these values in query.
     *
     * @param value Time value to be appended
     *
     * @return Value class which can be used to append
     * value (with comma in front) into 'VALUE (value(s))'
     * statement
     */
    public Value setTime(String value) {
        addCommaAfterFirstValue();
        ValueAppender.appendTimeParam(statement, value);
        return new Value(statement);
    }

    /**
     * Validates user input and appends integer 'value(s)'
     * into 'INSERT INTO table VALUE (value(s))' statement.
     *
     * SQL type TIMESTAMP maps to Timestamp. Use this method for
     * setting these values in query.
     *
     * @param value DateTime value to be appended
     *
     * @return Value class which can be used to append
     * value (with comma in front) into 'VALUE (value(s))'
     * statement
     */
    public Value setTimestamp(String value) {
        addCommaAfterFirstValue();
        ValueAppender.appendTimestampParam(statement, value);
        return new Value(statement);
    }

    /**
     * Validates user input and appends integer 'value(s)'
     * into 'INSERT INTO table VALUE (value(s))' statement.
     *
     * SQL types BOOLEAN, TINYINT and BIT maps to Timestamp.
     * Use this method for setting these values in query.
     *
     * @param value Boolean value to be appended
     *
     * @return Value class which can be used to append
     * value (with comma in front) into 'VALUE (value(s))'
     * statement
     */
    public Value setBoolean(boolean value) {
        addCommaAfterFirstValue();
        ValueAppender.appendBooleanParam(statement, value);
        return new Value(statement);
    }

    /**
     * Validates user input and appends integer 'value(s)'
     * into 'INSERT INTO table VALUE (value(s))' statement.
     *
     * SQL types BLOB, BINARY, VARBINARY and LONGVARBINARY
     * maps to Timestamp. Use this method for setting these
     * values in query.
     *
     * @param value Byte array value to be appended
     *
     * @return Value class which can be used to append
     * value (with comma in front) into 'VALUE (value(s))'
     * statement
     */
    public Value setByteArray(byte[] value) {
        addCommaAfterFirstValue();
        ValueAppender.appendByteArrayParam(statement, value);
        return new Value(statement);
    }

    protected abstract void addCommaAfterFirstValue();
}
