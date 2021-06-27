package builder.statement;

import builder.appender.ListValueAppender;
import builder.appender.SubQueryAppender;
import builder.appender.ValueAppender;
import builder.query.select.SelectQueryBuilder;
import query.Statement;

public class Comparison {
    private static final String EQUALS = " = ";
    private static final String LESSER_THAN = " < ";
    private static final String GREATER_THAN = " > ";
    private static final String LESSER_THAN_OR_EQUAL = " <= ";
    private static final String GREATER_THAN_OR_EQUAL = " >= ";
    private static final String ANY = "ANY ";
    private static final String ALL = "ALL ";

    protected Statement statement;

    public Comparison(Statement statement) {
        this.statement = statement;
    }

    /**
     * Appends '= value' into 'WHERE operand = value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition equalsString(String value) {
        statement.append(EQUALS);
        ValueAppender.appendStringParam(statement, value);
        return getCondition();
    }

    /**
     * Appends '= value' into 'WHERE operand = value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition equalsDate(String value) {
        statement.append(EQUALS);
        ValueAppender.appendDateParam(statement, value);
        return getCondition();
    }

    /**
     * Appends '= value' into 'WHERE operand = value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition equalsTimestamp(String value) {
        statement.append(EQUALS);
        ValueAppender.appendTimestampParam(statement, value);
        return getCondition();
    }

    /**
     * Appends '= value' into 'WHERE operand = value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition equalsInteger(int value) {
        statement.append(EQUALS);
        ValueAppender.appendIntParam(statement, value);
        return getCondition();
    }

    /**
     * Appends '= value' into 'WHERE operand = value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition equalsLong(long value) {
        statement.append(EQUALS);
        ValueAppender.appendLongParam(statement, value);
        return getCondition();
    }

    /**
     * Appends '= value' into 'WHERE operand = value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition equalsDouble(double value) {
        statement.append(EQUALS);
        ValueAppender.appendDoubleParam(statement, value);
        return getCondition();
    }

    /**
     * Appends '= value' into 'WHERE operand = value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition equalsBoolean(boolean value) {
        statement.append(EQUALS);
        ValueAppender.appendBooleanParam(statement, value);
        return getCondition();
    }

    /**
     * Appends '= ANY (select sub-query)' into
     * 'WHERE operand = ANY (select sub-query)' clause.
     *
     * @param subQuery SELECT sub-query to be appended. Sub-query
     * can be created with factory class QueryFactory
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition equalsAny(SelectQueryBuilder subQuery) {
        statement.append(EQUALS);
        statement.append(ANY);
        SubQueryAppender.appendSubQuery(statement, subQuery);
        return getCondition();
    }

    /**
     * Appends '= ALL (select sub-query)' into
     * 'WHERE operand = ALL (select sub-query)' clause.
     *
     * @param subQuery SELECT sub-query to be appended. Sub-query
     * can be created with factory class QueryFactory
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition equalsAll(SelectQueryBuilder subQuery) {
        statement.append(EQUALS);
        statement.append(ALL);
        SubQueryAppender.appendSubQuery(statement, subQuery);
        return getCondition();
    }

    /**
     * Appends '{@literal >} value' into 'WHERE operand {@literal >} value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition greaterThanString(String value) {
        statement.append(GREATER_THAN);
        ValueAppender.appendStringParam(statement, value);
        return getCondition();
    }

    /**
     * Appends '{@literal >} value' into 'WHERE operand {@literal >} value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition greaterThanDate(String value) {
        statement.append(GREATER_THAN);
        ValueAppender.appendDateParam(statement, value);
        return getCondition();
    }

    /**
     * Appends '{@literal >} value' into 'WHERE operand {@literal >} value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition greaterThanTimestamp(String value) {
        statement.append(GREATER_THAN);
        ValueAppender.appendTimestampParam(statement, value);
        return getCondition();
    }

    /**
     * Appends '{@literal >} value' into 'WHERE operand {@literal >} value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition greaterThanInteger(int value) {
        statement.append(GREATER_THAN);
        ValueAppender.appendIntParam(statement, value);
        return getCondition();
    }

    /**
     * Appends '{@literal >} value' into 'WHERE operand {@literal >} value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition greaterThanLong(long value) {
        statement.append(GREATER_THAN);
        ValueAppender.appendLongParam(statement, value);
        return getCondition();
    }

    /**
     * Appends '{@literal >} value' into 'WHERE operand {@literal >} value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition greaterThanDouble(double value) {
        statement.append(GREATER_THAN);
        ValueAppender.appendDoubleParam(statement, value);
        return getCondition();
    }

    /**
     * Appends '{@literal >} ANY (select sub-query)' into
     * 'WHERE operand {@literal >} ANY (select sub-query)' clause.
     *
     * @param subQuery SELECT sub-query to be appended. Sub-query
     * can be created with factory class QueryFactory
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition greaterThanAny(SelectQueryBuilder subQuery) {
        statement.append(GREATER_THAN);
        statement.append(ANY);
        SubQueryAppender.appendSubQuery(statement, subQuery);
        return getCondition();
    }

    /**
     * Appends '{@literal >} ALL (select sub-query)' into
     * 'WHERE operand {@literal >} ALL (select sub-query)' clause.
     *
     * @param subQuery SELECT sub-query to be appended. Sub-query
     * can be created with factory class QueryFactory
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition greaterThanAll(SelectQueryBuilder subQuery) {
        statement.append(GREATER_THAN);
        statement.append(ALL);
        SubQueryAppender.appendSubQuery(statement, subQuery);
        return getCondition();
    }

    /**
     * Appends '{@literal >=} value' into 'WHERE operand {@literal >=} value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition greaterThanOrEqualString(String value) {
        statement.append(GREATER_THAN_OR_EQUAL);
        ValueAppender.appendStringParam(statement, value);
        return getCondition();
    }

    /**
     * Appends '{@literal >=} value' into 'WHERE operand {@literal >=} value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition greaterThanOrEqualDate(String value) {
        statement.append(GREATER_THAN_OR_EQUAL);
        ValueAppender.appendDateParam(statement, value);
        return getCondition();
    }

    /**
     * Appends '{@literal >=} value' into 'WHERE operand {@literal >=} value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition greaterThanOrEqualTimestamp(String value) {
        statement.append(GREATER_THAN_OR_EQUAL);
        ValueAppender.appendTimestampParam(statement, value);
        return getCondition();
    }

    /**
     * Appends '{@literal >=} value' into 'WHERE operand {@literal >=} value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition greaterThanOrEqualInteger(int value) {
        statement.append(GREATER_THAN_OR_EQUAL);
        ValueAppender.appendIntParam(statement, value);
        return getCondition();
    }

    /**
     * Appends '{@literal >=} value' into 'WHERE operand {@literal >=} value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition greaterThanOrEqualLong(long value) {
        statement.append(GREATER_THAN_OR_EQUAL);
        ValueAppender.appendLongParam(statement, value);
        return getCondition();
    }

    /**
     * Appends '{@literal >=} value' into 'WHERE operand {@literal >=} value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition greaterThanOrEqualDouble(double value) {
        statement.append(GREATER_THAN_OR_EQUAL);
        ValueAppender.appendDoubleParam(statement, value);
        return getCondition();
    }

    /**
     * Appends '{@literal >=} ANY (select sub-query)' into
     * 'WHERE operand {@literal >=} ANY (select sub-query)' clause.
     *
     * @param subQuery SELECT sub-query to be appended. Sub-query
     * can be created with factory class QueryFactory
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition greaterThanOrEqualAny(SelectQueryBuilder subQuery) {
        statement.append(GREATER_THAN_OR_EQUAL);
        statement.append(ANY);
        SubQueryAppender.appendSubQuery(statement, subQuery);
        return getCondition();
    }

    /**
     * Appends '{@literal >=} ALL (select sub-query)' into
     * 'WHERE operand {@literal >=} ALL (select sub-query)' clause.
     *
     * @param subQuery SELECT sub-query to be appended. Sub-query
     * can be created with factory class QueryFactory
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition greaterThanOrEqualAll(SelectQueryBuilder subQuery) {
        statement.append(GREATER_THAN_OR_EQUAL);
        statement.append(ALL);
        SubQueryAppender.appendSubQuery(statement, subQuery);
        return getCondition();
    }

    /**
     * Appends '{@literal <} value' into 'WHERE operand {@literal <} value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition lesserThanString(String value) {
        statement.append(LESSER_THAN);
        ValueAppender.appendStringParam(statement, value);
        return getCondition();
    }

    /**
     * Appends '{@literal <} value' into 'WHERE operand {@literal <} value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition lesserThanDate(String value) {
        statement.append(LESSER_THAN);
        ValueAppender.appendDateParam(statement, value);
        return getCondition();
    }

    /**
     * Appends '{@literal <} value' into 'WHERE operand {@literal <} value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition lesserThanTimestamp(String value) {
        statement.append(LESSER_THAN);
        ValueAppender.appendTimestampParam(statement, value);
        return getCondition();
    }

    /**
     * Appends '{@literal <} value' into 'WHERE operand {@literal <} value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition lesserThanInteger(int value) {
        statement.append(LESSER_THAN);
        ValueAppender.appendIntParam(statement, value);
        return getCondition();
    }

    /**
     * Appends '{@literal <} value' into 'WHERE operand {@literal <} value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition lesserThanLong(long value) {
        statement.append(LESSER_THAN);
        ValueAppender.appendLongParam(statement, value);
        return getCondition();
    }

    /**
     * Appends '{@literal <} value' into 'WHERE operand {@literal <} value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition lesserThanDouble(double value) {
        statement.append(LESSER_THAN);
        ValueAppender.appendDoubleParam(statement, value);
        return getCondition();
    }

    /**
     * Appends '{@literal <} ANY (select sub-query)' into
     * 'WHERE operand {@literal <} ANY (select sub-query)' clause.
     *
     * @param subQuery SELECT sub-query to be appended. Sub-query
     * can be created with factory class QueryFactory
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition lesserThanAny(SelectQueryBuilder subQuery) {
        statement.append(LESSER_THAN);
        statement.append(ANY);
        SubQueryAppender.appendSubQuery(statement, subQuery);
        return getCondition();
    }

    /**
     * Appends '{@literal <} ALL (select sub-query)' into
     * 'WHERE operand {@literal <} ALL (select sub-query)' clause.
     *
     * @param subQuery SELECT sub-query to be appended. Sub-query
     * can be created with factory class QueryFactory
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition lesserThanAll(SelectQueryBuilder subQuery) {
        statement.append(LESSER_THAN);
        statement.append(ALL);
        SubQueryAppender.appendSubQuery(statement, subQuery);
        return getCondition();
    }

    /**
     * Appends '{@literal <=} value' into 'WHERE operand {@literal <=} value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition lesserThanOrEqualString(String value) {
        statement.append(LESSER_THAN_OR_EQUAL);
        ValueAppender.appendStringParam(statement, value);
        return getCondition();
    }

    /**
     * Appends '{@literal <=} value' into 'WHERE operand {@literal <=} value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition lesserThanOrEqualDate(String value) {
        statement.append(LESSER_THAN_OR_EQUAL);
        ValueAppender.appendDateParam(statement, value);
        return getCondition();
    }

    /**
     * Appends '{@literal <=} value' into 'WHERE operand {@literal <=} value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition lesserThanOrEqualTimestamp(String value) {
        statement.append(LESSER_THAN_OR_EQUAL);
        ValueAppender.appendTimestampParam(statement, value);
        return getCondition();
    }

    /**
     * Appends '{@literal <=} value' into 'WHERE operand {@literal <=} value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition lesserThanOrEqualInteger(int value) {
        statement.append(LESSER_THAN_OR_EQUAL);
        ValueAppender.appendIntParam(statement, value);
        return getCondition();
    }

    /**
     * Appends '{@literal <=} value' into 'WHERE operand {@literal <=} value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition lesserThanOrEqualLong(long value) {
        statement.append(LESSER_THAN_OR_EQUAL);
        ValueAppender.appendLongParam(statement, value);
        return getCondition();
    }

    /**
     * Appends '{@literal <=} value' into 'WHERE operand {@literal <=} value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition lesserThanOrEqualDouble(double value) {
        statement.append(LESSER_THAN_OR_EQUAL);
        ValueAppender.appendDoubleParam(statement, value);
        return getCondition();
    }

    /**
     * Appends '{@literal <=} ANY (select sub-query)' into
     * 'WHERE operand {@literal <=} ANY (select sub-query)' clause.
     *
     * @param subQuery SELECT sub-query to be appended. Sub-query
     * can be created with factory class QueryFactory
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition lesserThanOrEqualAny(SelectQueryBuilder subQuery) {
        statement.append(LESSER_THAN_OR_EQUAL);
        statement.append(ANY);
        SubQueryAppender.appendSubQuery(statement, subQuery);
        return getCondition();
    }

    /**
     * Appends '{@literal <=} ALL (select sub-query)' into
     * 'WHERE operand {@literal <=} ALL (select sub-query)' clause.
     *
     * @param subQuery SELECT sub-query to be appended. Sub-query
     * can be created with factory class QueryFactory
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition lesserThanOrEqualAll(SelectQueryBuilder subQuery) {
        statement.append(LESSER_THAN_OR_EQUAL);
        statement.append(ALL);
        SubQueryAppender.appendSubQuery(statement, subQuery);
        return getCondition();
    }

    /**
     * Appends 'LIKE pattern%' into 'WHERE operand LIKE pattern%' clause.
     *
     * @param pattern String to be appended as pattern to match
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition startsWith(String pattern) {
        String startsWith = pattern.concat("%");
        statement.append(" LIKE ");
        ValueAppender.appendStringParam(statement, startsWith);
        return getCondition();
    }

    /**
     * Appends 'LIKE %pattern' into 'WHERE operand LIKE %pattern' clause.
     *
     * @param pattern String to be appended as pattern to match
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition endsWith(String pattern) {
        String endsWith = "%".concat(pattern);
        statement.append(" LIKE ");
        ValueAppender.appendStringParam(statement, endsWith);
        return getCondition();
    }

    /**
     * Appends 'LIKE %pattern%' into 'WHERE operand LIKE %pattern%' clause.
     *
     * @param pattern String to be appended as pattern to match
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition contains(String pattern) {
        String contains = "%".concat(pattern).concat("%");
        statement.append(" LIKE ");
        ValueAppender.appendStringParam(statement, contains);
        return getCondition();
    }

    /**
     * Appends 'BETWEEN lower AND higher' into 'WHERE operand
     * BETWEEN lower AND higher' clause.
     *
     * @param lower Lower value of BETWEEN comparison
     *
     * @param higher Higher value of BETWEEN comparison
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition isBetweenString(String lower, String higher) {
        statement.append(" BETWEEN ");
        ValueAppender.appendStringParam(statement, lower);
        statement.append(" AND ");
        ValueAppender.appendStringParam(statement, higher);
        return getCondition();
    }

    /**
     * Appends 'BETWEEN lower AND higher' into 'WHERE operand
     * BETWEEN lower AND higher' clause.
     *
     * @param lower Lower value of BETWEEN comparison
     *
     * @param higher Higher value of BETWEEN comparison
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition isBetweenDate(String lower, String higher) {
        statement.append(" BETWEEN ");
        ValueAppender.appendDateParam(statement, lower);
        statement.append(" AND ");
        ValueAppender.appendDateParam(statement, higher);
        return getCondition();
    }

    /**
     * Appends 'BETWEEN lower AND higher' into 'WHERE operand
     * BETWEEN lower AND higher' clause.
     *
     * @param lower Lower value of BETWEEN comparison
     *
     * @param higher Higher value of BETWEEN comparison
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition isBetweenTimestamp(String lower, String higher) {
        statement.append(" BETWEEN ");
        ValueAppender.appendTimestampParam(statement, lower);
        statement.append(" AND ");
        ValueAppender.appendTimestampParam(statement, higher);
        return getCondition();
    }

    /**
     * Appends 'BETWEEN lower AND higher' into 'WHERE operand
     * BETWEEN lower AND higher' clause.
     *
     * @param lower Lower value of BETWEEN comparison
     *
     * @param higher Higher value of BETWEEN comparison
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition isBetweenInteger(int lower, int higher) {
        statement.append(" BETWEEN ");
        ValueAppender.appendIntParam(statement, lower);
        statement.append(" AND ");
        ValueAppender.appendIntParam(statement, higher);
        return getCondition();
    }

    /**
     * Appends 'BETWEEN lower AND higher' into 'WHERE operand
     * BETWEEN lower AND higher' clause.
     *
     * @param lower Lower value of BETWEEN comparison
     *
     * @param higher Higher value of BETWEEN comparison
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition isBetweenLong(long lower, long higher) {
        statement.append(" BETWEEN ");
        ValueAppender.appendLongParam(statement, lower);
        statement.append(" AND ");
        ValueAppender.appendLongParam(statement, higher);
        return getCondition();
    }

    /**
     * Appends 'BETWEEN lower AND higher' into 'WHERE operand
     * BETWEEN lower AND higher' clause.
     *
     * @param lower Lower value of BETWEEN comparison
     *
     * @param higher Higher value of BETWEEN comparison
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition isBetweenDouble(double lower, double higher) {
        statement.append(" BETWEEN ");
        ValueAppender.appendDoubleParam(statement, lower);
        statement.append(" AND ");
        ValueAppender.appendDoubleParam(statement, higher);
        return getCondition();
    }

    /**
     * Appends 'IS NULL' into 'WHERE operand IS NULL' clause.
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition isNull() {
        statement.append(" IS NULL");
        return getCondition();
    }

    /**
     * Appends 'IS NOT NULL' into 'WHERE operand IS NOT NULL' clause.
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition isNotNull() {
        statement.append(" IS NOT NULL");
        return getCondition();
    }

    /**
     * Appends 'IN (listOfValues)' into 'WHERE operand IN (listOfValues)' clause.
     *
     * @param listOfValues Values operand is being compared
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition isInString(String ...listOfValues) {
        statement.append(" IN ");
        ListValueAppender.appendListOfStringParams(statement, listOfValues);
        return getCondition();
    }

    /**
     * Appends 'IN (listOfValues)' into 'WHERE operand IN (listOfValues)' clause.
     *
     * @param listOfValues Values operand is being compared
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition isInDate(String ...listOfValues) {
        statement.append(" IN ");
        ListValueAppender.appendListOfDateParams(statement, listOfValues);
        return getCondition();
    }

    /**
     * Appends 'IN (listOfValues)' into 'WHERE operand IN (listOfValues)' clause.
     *
     * @param listOfValues Values operand is being compared
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition isInDateTime(String ...listOfValues) {
        statement.append(" IN ");
        ListValueAppender.appendListOfTimestampParams(statement, listOfValues);
        return getCondition();
    }

    /**
     * Appends 'IN (listOfValues)' into 'WHERE operand IN (listOfValues)' clause.
     *
     * @param listOfValues Values operand is being compared
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition isInInteger(int ...listOfValues) {
        statement.append(" IN ");
        ListValueAppender.appendListOfIntParams(statement, listOfValues);
        return getCondition();
    }

    /**
     * Appends 'IN (listOfValues)' into 'WHERE operand IN (listOfValues)' clause.
     *
     * @param listOfValues Values operand is being compared
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition isInLong(long ...listOfValues) {
        statement.append(" IN ");
        ListValueAppender.appendListOfLongParams(statement, listOfValues);
        return getCondition();
    }

    /**
     * Appends 'IN (listOfValues)' into 'WHERE operand IN (listOfValues)' clause.
     *
     * @param listOfValues Values operand is being compared
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition isInDouble(double ...listOfValues) {
        statement.append(" IN ");
        ListValueAppender.appendListOfDoubleParams(statement, listOfValues);
        return getCondition();
    }

    /**
     * Appends 'IN (select sub-query)' into
     * 'WHERE operand IN (select sub-query)' clause.
     *
     * @param subQuery SELECT sub-query to be appended. Sub-query
     * can be created with factory class QueryFactory
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition isInSub(SelectQueryBuilder subQuery) {
        statement.append(" IN ");
        SubQueryAppender.appendSubQuery(statement, subQuery);
        return getCondition();
    }

    private Condition getCondition() {
        return new Condition(statement);
    }
}
