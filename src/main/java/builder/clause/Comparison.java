package builder.clause;

import builder.appender.ListValueAppender;
import builder.appender.ValueAppender;
import builder.statement.select.SelectQueryBuilder;
import builder.appender.SubQueryAppender;
import query.SQLQuery;

public class Comparison {
    private static final String EQUALS = " = ";
    private static final String LESSER_THAN = " < ";
    private static final String GREATER_THAN = " > ";
    private static final String LESSER_THAN_OR_EQUAL = " <= ";
    private static final String GREATER_THAN_OR_EQUAL = " >= ";

    private SubQueryAppender subQueryAppender;

    protected SQLQuery query;

    public Comparison(SQLQuery query) {
        this.query = query;
        this.subQueryAppender = new SubQueryAppender(query);
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
    public Condition equals(String value) {
        query.append(EQUALS);
        ValueAppender.appendStringParam(query, value);
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
    public Condition equals(int value) {
        query.append(EQUALS);
        query.append(value);
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
    public Condition equals(double value) {
        query.append(EQUALS);
        query.append(value);
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
        query.append(EQUALS);
        subQueryAppender.appendConditionWithSubQuery("ANY ", subQuery);
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
        query.append(EQUALS);
        subQueryAppender.appendConditionWithSubQuery("ALL ", subQuery);
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
    public Condition greaterThan(String value) {
        query.append(GREATER_THAN);
        ValueAppender.appendStringParam(query, value);
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
    public Condition greaterThan(int value) {
        query.append(GREATER_THAN);
        query.append(value);
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
    public Condition greaterThan(double value) {
        query.append(GREATER_THAN);
        query.append(value);
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
        query.append(GREATER_THAN);
        subQueryAppender.appendConditionWithSubQuery("ANY ", subQuery);
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
        query.append(GREATER_THAN);
        subQueryAppender.appendConditionWithSubQuery("ALL ", subQuery);
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
    public Condition greaterThanOrEqual(String value) {
        query.append(GREATER_THAN_OR_EQUAL);
        ValueAppender.appendStringParam(query, value);
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
    public Condition greaterThanOrEqual(int value) {
        query.append(GREATER_THAN_OR_EQUAL);
        query.append(value);
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
    public Condition greaterThanOrEqual(double value) {
        query.append(GREATER_THAN_OR_EQUAL);
        query.append(value);
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
        query.append(GREATER_THAN_OR_EQUAL);
        subQueryAppender.appendConditionWithSubQuery("ANY ", subQuery);
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
        query.append(GREATER_THAN_OR_EQUAL);
        subQueryAppender.appendConditionWithSubQuery("ALL ", subQuery);
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
    public Condition lesserThan(String value) {
        query.append(LESSER_THAN);
        ValueAppender.appendStringParam(query, value);
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
    public Condition lesserThan(int value) {
        query.append(LESSER_THAN);
        query.append(value);
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
    public Condition lesserThan(double value) {
        query.append(LESSER_THAN);
        query.append(value);
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
        query.append(LESSER_THAN);
        subQueryAppender.appendConditionWithSubQuery("ANY ", subQuery);
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
        query.append(LESSER_THAN);
        subQueryAppender.appendConditionWithSubQuery("ALL ", subQuery);
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
    public Condition lesserThanOrEqual(String value) {
        query.append(LESSER_THAN_OR_EQUAL);
        ValueAppender.appendStringParam(query, value);
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
    public Condition lesserThanOrEqual(int value) {
        query.append(LESSER_THAN_OR_EQUAL);
        query.append(value);
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
    public Condition lesserThanOrEqual(double value) {
        query.append(LESSER_THAN_OR_EQUAL);
        query.append(value);
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
        query.append(LESSER_THAN_OR_EQUAL);
        subQueryAppender.appendConditionWithSubQuery("ANY ", subQuery);
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
        query.append(LESSER_THAN_OR_EQUAL);
        subQueryAppender.appendConditionWithSubQuery("ALL ", subQuery);
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
        query.append(" LIKE ");
        ValueAppender.appendStringParam(query, startsWith);
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
        query.append(" LIKE ");
        ValueAppender.appendStringParam(query, endsWith);
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
        query.append(" LIKE ");
        ValueAppender.appendStringParam(query, contains);
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
    public Condition isBetween(String lower, String higher) {
        query.append(" BETWEEN ");
        ValueAppender.appendStringParam(query, lower);
        query.append(" AND ");
        ValueAppender.appendStringParam(query, higher);
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
    public Condition isBetween(int lower, int higher) {
        query.append(" BETWEEN ");
        query.append(lower);
        query.append(" AND ");
        query.append(higher);
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
    public Condition isBetween(double lower, double higher) {
        query.append(" BETWEEN ");
        query.append(lower);
        query.append(" AND ");
        query.append(higher);
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
        query.append(" IS NULL");
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
        query.append(" IS NOT NULL");
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
    public Condition isIn(String ...listOfValues) {
        query.append(" IN ");
        ListValueAppender.appendListOfStringParams(query, listOfValues);
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
    public Condition isIn(int ...listOfValues) {
        query.append(" IN ");
        ListValueAppender.appendListOfIntParams(query, listOfValues);
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
    public Condition isIn(double ...listOfValues) {
        query.append(" IN ");
        ListValueAppender.appendListOfDoubleParams(query, listOfValues);
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
        subQueryAppender.appendConditionWithSubQuery(" IN ", subQuery);
        return getCondition();
    }

    private Condition getCondition() {
        return new Condition(query);
    }
}
