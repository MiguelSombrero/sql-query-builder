package builder.clause;

import builder.appender.ListValueAppender;
import builder.appender.SubQueryAppender;
import builder.appender.ValueAppender;
import builder.query.select.SelectQueryBuilder;
import clause.SQLClause;

public class Comparison {
    private static final String EQUALS = " = ";
    private static final String LESSER_THAN = " < ";
    private static final String GREATER_THAN = " > ";
    private static final String LESSER_THAN_OR_EQUAL = " <= ";
    private static final String GREATER_THAN_OR_EQUAL = " >= ";
    private static final String ANY = "ANY ";
    private static final String ALL = "ALL ";

    protected SQLClause clause;

    public Comparison(SQLClause clause) {
        this.clause = clause;
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
        clause.append(EQUALS);
        ValueAppender.appendStringParam(clause, value);
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
        clause.append(EQUALS);
        ValueAppender.appendDateParam(clause, value);
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
    public Condition equalsDateTime(String value) {
        clause.append(EQUALS);
        ValueAppender.appendDateTimeParam(clause, value);
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
        clause.append(EQUALS);
        ValueAppender.appendIntParam(clause, value);
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
        clause.append(EQUALS);
        ValueAppender.appendLongParam(clause, value);
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
        clause.append(EQUALS);
        ValueAppender.appendDoubleParam(clause, value);
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
        clause.append(EQUALS);
        ValueAppender.appendBooleanParam(clause, value);
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
        clause.append(EQUALS);
        clause.append(ANY);
        SubQueryAppender.appendSubQuery(clause, subQuery);
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
        clause.append(EQUALS);
        clause.append(ALL);
        SubQueryAppender.appendSubQuery(clause, subQuery);
        return getCondition();
    }

    /**
     * Appends '> value' into 'WHERE operand > value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition greaterThanString(String value) {
        clause.append(GREATER_THAN);
        ValueAppender.appendStringParam(clause, value);
        return getCondition();
    }

    /**
     * Appends '> value' into 'WHERE operand > value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition greaterThanDate(String value) {
        clause.append(GREATER_THAN);
        ValueAppender.appendDateParam(clause, value);
        return getCondition();
    }

    /**
     * Appends '> value' into 'WHERE operand > value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition greaterThanDateTime(String value) {
        clause.append(GREATER_THAN);
        ValueAppender.appendDateTimeParam(clause, value);
        return getCondition();
    }

    /**
     * Appends '> value' into 'WHERE operand > value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition greaterThanInteger(int value) {
        clause.append(GREATER_THAN);
        ValueAppender.appendIntParam(clause, value);
        return getCondition();
    }

    /**
     * Appends '> value' into 'WHERE operand > value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition greaterThanLong(long value) {
        clause.append(GREATER_THAN);
        ValueAppender.appendLongParam(clause, value);
        return getCondition();
    }

    /**
     * Appends '> value' into 'WHERE operand > value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition greaterThanDouble(double value) {
        clause.append(GREATER_THAN);
        ValueAppender.appendDoubleParam(clause, value);
        return getCondition();
    }

    /**
     * Appends '> ANY (select sub-query)' into
     * 'WHERE operand > ANY (select sub-query)' clause.
     *
     * @param subQuery SELECT sub-query to be appended. Sub-query
     * can be created with factory class QueryFactory
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition greaterThanAny(SelectQueryBuilder subQuery) {
        clause.append(GREATER_THAN);
        clause.append(ANY);
        SubQueryAppender.appendSubQuery(clause, subQuery);
        return getCondition();
    }

    /**
     * Appends '> ALL (select sub-query)' into
     * 'WHERE operand > ALL (select sub-query)' clause.
     *
     * @param subQuery SELECT sub-query to be appended. Sub-query
     * can be created with factory class QueryFactory
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition greaterThanAll(SelectQueryBuilder subQuery) {
        clause.append(GREATER_THAN);
        clause.append(ALL);
        SubQueryAppender.appendSubQuery(clause, subQuery);
        return getCondition();
    }

    /**
     * Appends '>= value' into 'WHERE operand >= value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition greaterThanOrEqualString(String value) {
        clause.append(GREATER_THAN_OR_EQUAL);
        ValueAppender.appendStringParam(clause, value);
        return getCondition();
    }

    /**
     * Appends '>= value' into 'WHERE operand >= value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition greaterThanOrEqualDate(String value) {
        clause.append(GREATER_THAN_OR_EQUAL);
        ValueAppender.appendDateParam(clause, value);
        return getCondition();
    }

    /**
     * Appends '>= value' into 'WHERE operand >= value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition greaterThanOrEqualDateTime(String value) {
        clause.append(GREATER_THAN_OR_EQUAL);
        ValueAppender.appendDateTimeParam(clause, value);
        return getCondition();
    }

    /**
     * Appends '>= value' into 'WHERE operand >= value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition greaterThanOrEqualInteger(int value) {
        clause.append(GREATER_THAN_OR_EQUAL);
        ValueAppender.appendIntParam(clause, value);
        return getCondition();
    }

    /**
     * Appends '>= value' into 'WHERE operand >= value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition greaterThanOrEqualLong(long value) {
        clause.append(GREATER_THAN_OR_EQUAL);
        ValueAppender.appendLongParam(clause, value);
        return getCondition();
    }

    /**
     * Appends '>= value' into 'WHERE operand >= value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition greaterThanOrEqualDouble(double value) {
        clause.append(GREATER_THAN_OR_EQUAL);
        ValueAppender.appendDoubleParam(clause, value);
        return getCondition();
    }

    /**
     * Appends '>= ANY (select sub-query)' into
     * 'WHERE operand >= ANY (select sub-query)' clause.
     *
     * @param subQuery SELECT sub-query to be appended. Sub-query
     * can be created with factory class QueryFactory
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition greaterThanOrEqualAny(SelectQueryBuilder subQuery) {
        clause.append(GREATER_THAN_OR_EQUAL);
        clause.append(ANY);
        SubQueryAppender.appendSubQuery(clause, subQuery);
        return getCondition();
    }

    /**
     * Appends '>= ALL (select sub-query)' into
     * 'WHERE operand >= ALL (select sub-query)' clause.
     *
     * @param subQuery SELECT sub-query to be appended. Sub-query
     * can be created with factory class QueryFactory
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition greaterThanOrEqualAll(SelectQueryBuilder subQuery) {
        clause.append(GREATER_THAN_OR_EQUAL);
        clause.append(ALL);
        SubQueryAppender.appendSubQuery(clause, subQuery);
        return getCondition();
    }

    /**
     * Appends '< value' into 'WHERE operand < value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition lesserThanString(String value) {
        clause.append(LESSER_THAN);
        ValueAppender.appendStringParam(clause, value);
        return getCondition();
    }

    /**
     * Appends '< value' into 'WHERE operand < value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition lesserThanDate(String value) {
        clause.append(LESSER_THAN);
        ValueAppender.appendDateParam(clause, value);
        return getCondition();
    }

    /**
     * Appends '< value' into 'WHERE operand < value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition lesserThanDateTime(String value) {
        clause.append(LESSER_THAN);
        ValueAppender.appendDateTimeParam(clause, value);
        return getCondition();
    }

    /**
     * Appends '< value' into 'WHERE operand < value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition lesserThanInteger(int value) {
        clause.append(LESSER_THAN);
        ValueAppender.appendIntParam(clause, value);
        return getCondition();
    }

    /**
     * Appends '< value' into 'WHERE operand < value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition lesserThanLong(long value) {
        clause.append(LESSER_THAN);
        ValueAppender.appendLongParam(clause, value);
        return getCondition();
    }

    /**
     * Appends '< value' into 'WHERE operand < value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition lesserThanDouble(double value) {
        clause.append(LESSER_THAN);
        ValueAppender.appendDoubleParam(clause, value);
        return getCondition();
    }

    /**
     * Appends '< ANY (select sub-query)' into
     * 'WHERE operand < ANY (select sub-query)' clause.
     *
     * @param subQuery SELECT sub-query to be appended. Sub-query
     * can be created with factory class QueryFactory
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition lesserThanAny(SelectQueryBuilder subQuery) {
        clause.append(LESSER_THAN);
        clause.append(ANY);
        SubQueryAppender.appendSubQuery(clause, subQuery);
        return getCondition();
    }

    /**
     * Appends '< ALL (select sub-query)' into
     * 'WHERE operand < ALL (select sub-query)' clause.
     *
     * @param subQuery SELECT sub-query to be appended. Sub-query
     * can be created with factory class QueryFactory
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition lesserThanAll(SelectQueryBuilder subQuery) {
        clause.append(LESSER_THAN);
        clause.append(ALL);
        SubQueryAppender.appendSubQuery(clause, subQuery);
        return getCondition();
    }

    /**
     * Appends '<= value' into 'WHERE operand <= value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition lesserThanOrEqualString(String value) {
        clause.append(LESSER_THAN_OR_EQUAL);
        ValueAppender.appendStringParam(clause, value);
        return getCondition();
    }

    /**
     * Appends '<= value' into 'WHERE operand <= value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition lesserThanOrEqualDate(String value) {
        clause.append(LESSER_THAN_OR_EQUAL);
        ValueAppender.appendDateParam(clause, value);
        return getCondition();
    }

    /**
     * Appends '<= value' into 'WHERE operand <= value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition lesserThanOrEqualDateTime(String value) {
        clause.append(LESSER_THAN_OR_EQUAL);
        ValueAppender.appendDateTimeParam(clause, value);
        return getCondition();
    }

    /**
     * Appends '<= value' into 'WHERE operand <= value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition lesserThanOrEqualInteger(int value) {
        clause.append(LESSER_THAN_OR_EQUAL);
        ValueAppender.appendIntParam(clause, value);
        return getCondition();
    }

    /**
     * Appends '<= value' into 'WHERE operand <= value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition lesserThanOrEqualLong(long value) {
        clause.append(LESSER_THAN_OR_EQUAL);
        ValueAppender.appendLongParam(clause, value);
        return getCondition();
    }

    /**
     * Appends '<= value' into 'WHERE operand <= value' clause.
     *
     * @param value Value to be appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition lesserThanOrEqualDouble(double value) {
        clause.append(LESSER_THAN_OR_EQUAL);
        ValueAppender.appendDoubleParam(clause, value);
        return getCondition();
    }

    /**
     * Appends '<= ANY (select sub-query)' into
     * 'WHERE operand <= ANY (select sub-query)' clause.
     *
     * @param subQuery SELECT sub-query to be appended. Sub-query
     * can be created with factory class QueryFactory
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition lesserThanOrEqualAny(SelectQueryBuilder subQuery) {
        clause.append(LESSER_THAN_OR_EQUAL);
        clause.append(ANY);
        SubQueryAppender.appendSubQuery(clause, subQuery);
        return getCondition();
    }

    /**
     * Appends '<= ALL (select sub-query)' into
     * 'WHERE operand <= ALL (select sub-query)' clause.
     *
     * @param subQuery SELECT sub-query to be appended. Sub-query
     * can be created with factory class QueryFactory
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition lesserThanOrEqualAll(SelectQueryBuilder subQuery) {
        clause.append(LESSER_THAN_OR_EQUAL);
        clause.append(ALL);
        SubQueryAppender.appendSubQuery(clause, subQuery);
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
        clause.append(" LIKE ");
        ValueAppender.appendStringParam(clause, startsWith);
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
        clause.append(" LIKE ");
        ValueAppender.appendStringParam(clause, endsWith);
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
        clause.append(" LIKE ");
        ValueAppender.appendStringParam(clause, contains);
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
        clause.append(" BETWEEN ");
        ValueAppender.appendStringParam(clause, lower);
        clause.append(" AND ");
        ValueAppender.appendStringParam(clause, higher);
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
        clause.append(" BETWEEN ");
        ValueAppender.appendDateParam(clause, lower);
        clause.append(" AND ");
        ValueAppender.appendDateParam(clause, higher);
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
    public Condition isBetweenDateTime(String lower, String higher) {
        clause.append(" BETWEEN ");
        ValueAppender.appendDateTimeParam(clause, lower);
        clause.append(" AND ");
        ValueAppender.appendDateTimeParam(clause, higher);
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
        clause.append(" BETWEEN ");
        ValueAppender.appendIntParam(clause, lower);
        clause.append(" AND ");
        ValueAppender.appendIntParam(clause, higher);
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
        clause.append(" BETWEEN ");
        ValueAppender.appendLongParam(clause, lower);
        clause.append(" AND ");
        ValueAppender.appendLongParam(clause, higher);
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
        clause.append(" BETWEEN ");
        ValueAppender.appendDoubleParam(clause, lower);
        clause.append(" AND ");
        ValueAppender.appendDoubleParam(clause, higher);
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
        clause.append(" IS NULL");
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
        clause.append(" IS NOT NULL");
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
        clause.append(" IN ");
        ListValueAppender.appendListOfStringParams(clause, listOfValues);
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
        clause.append(" IN ");
        ListValueAppender.appendListOfDateParams(clause, listOfValues);
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
        clause.append(" IN ");
        ListValueAppender.appendListOfDateTimeParams(clause, listOfValues);
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
        clause.append(" IN ");
        ListValueAppender.appendListOfIntParams(clause, listOfValues);
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
        clause.append(" IN ");
        ListValueAppender.appendListOfLongParams(clause, listOfValues);
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
        clause.append(" IN ");
        ListValueAppender.appendListOfDoubleParams(clause, listOfValues);
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
        clause.append(" IN ");
        SubQueryAppender.appendSubQuery(clause, subQuery);
        return getCondition();
    }

    private Condition getCondition() {
        return new Condition(clause);
    }
}
