package builder.condition;

import query.SQLQuery;
import builder.utils.NumberAppender;
import builder.statement.select.SelectQueryBuilder;
import builder.utils.StringValueAppender;
import builder.utils.SubQueryAppender;

public class Comparison {
    private static final String EQUALS = " = ";
    private static final String LESSER_THAN = " < ";
    private static final String GREATER_THAN = " > ";
    private static final String LESSER_THAN_OR_EQUAL = " <= ";
    private static final String GREATER_THAN_OR_EQUAL = " >= ";

    private StringValueAppender stringValueAppender;
    private SubQueryAppender subQueryAppender;
    private NumberAppender numberAppender;

    protected SQLQuery SQLQuery;

    public Comparison(SQLQuery SQLQuery) {
        this.SQLQuery = SQLQuery;
        this.stringValueAppender = new StringValueAppender(SQLQuery);
        this.subQueryAppender = new SubQueryAppender(SQLQuery);
        this.numberAppender = new NumberAppender(SQLQuery);
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
        SQLQuery.append(EQUALS);
        stringValueAppender.validateAndAppendStringValue(value);
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
        SQLQuery.append(EQUALS);
        SQLQuery.append(value);
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
        SQLQuery.append(EQUALS);
        SQLQuery.append(value);
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
        SQLQuery.append(EQUALS);
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
        SQLQuery.append(EQUALS);
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
        SQLQuery.append(GREATER_THAN);
        stringValueAppender.validateAndAppendStringValue(value);
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
        SQLQuery.append(GREATER_THAN);
        SQLQuery.append(value);
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
        SQLQuery.append(GREATER_THAN);
        SQLQuery.append(value);
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
        SQLQuery.append(GREATER_THAN);
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
        SQLQuery.append(GREATER_THAN);
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
        SQLQuery.append(GREATER_THAN_OR_EQUAL);
        stringValueAppender.validateAndAppendStringValue(value);
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
        SQLQuery.append(GREATER_THAN_OR_EQUAL);
        SQLQuery.append(value);
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
        SQLQuery.append(GREATER_THAN_OR_EQUAL);
        SQLQuery.append(value);
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
        SQLQuery.append(GREATER_THAN_OR_EQUAL);
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
        SQLQuery.append(GREATER_THAN_OR_EQUAL);
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
        SQLQuery.append(LESSER_THAN);
        stringValueAppender.validateAndAppendStringValue(value);
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
        SQLQuery.append(LESSER_THAN);
        SQLQuery.append(value);
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
        SQLQuery.append(LESSER_THAN);
        SQLQuery.append(value);
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
        SQLQuery.append(LESSER_THAN);
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
        SQLQuery.append(LESSER_THAN);
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
        SQLQuery.append(LESSER_THAN_OR_EQUAL);
        stringValueAppender.validateAndAppendStringValue(value);
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
        SQLQuery.append(LESSER_THAN_OR_EQUAL);
        SQLQuery.append(value);
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
        SQLQuery.append(LESSER_THAN_OR_EQUAL);
        SQLQuery.append(value);
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
        SQLQuery.append(LESSER_THAN_OR_EQUAL);
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
        SQLQuery.append(LESSER_THAN_OR_EQUAL);
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
        stringValueAppender.validate(pattern);
        String startsWith = pattern.concat("%");
        SQLQuery.append(" LIKE ");
        stringValueAppender.appendStringValue(startsWith);
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
        stringValueAppender.validate(pattern);
        String endsWith = "%".concat(pattern);
        SQLQuery.append(" LIKE ");
        stringValueAppender.appendStringValue(endsWith);
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
        stringValueAppender.validate(pattern);
        String contains = "%".concat(pattern).concat("%");
        SQLQuery.append(" LIKE ");
        stringValueAppender.appendStringValue(contains);
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
        SQLQuery.append(" BETWEEN ");
        stringValueAppender.validateAndAppendStringValue(lower);
        SQLQuery.append(" AND ");
        stringValueAppender.validateAndAppendStringValue(higher);
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
        SQLQuery.append(" BETWEEN ");
        SQLQuery.append(lower);
        SQLQuery.append(" AND ");
        SQLQuery.append(higher);
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
        SQLQuery.append(" BETWEEN ");
        SQLQuery.append(lower);
        SQLQuery.append(" AND ");
        SQLQuery.append(higher);
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
        SQLQuery.append(" IS NULL");
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
        SQLQuery.append(" IS NOT NULL");
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
        SQLQuery.append(" IN ");
        stringValueAppender.validateAndAppendListOfValues(listOfValues);
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
        SQLQuery.append(" IN ");
        numberAppender.appendListOfValues(listOfValues);
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
        SQLQuery.append(" IN ");
        numberAppender.appendListOfValues(listOfValues);
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
        return new Condition(SQLQuery);
    }
}
