package builder.condition;

import builder.Query;
import builder.utils.QueryAppender;
import builder.statement.select.SelectQueryBuilder;
import builder.utils.StringValueAppender;
import builder.utils.SubQueryAppender;

public class Comparison {
    private static final String EQUALS = " = ";
    private static final String LESSER_THAN = " < ";
    private static final String GREATER_THAN = " > ";
    private static final String LESSER_THAN_OR_EQUAL = " <= ";
    private static final String GREATER_THAN_OR_EQUAL = " >= ";

    protected Query query;

    private StringValueAppender stringValueAppender;
    private SubQueryAppender subQueryAppender;

    public Comparison(Query query) {
        this.query = query;
        this.stringValueAppender = new StringValueAppender(query);
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
        stringValueAppender.validateAndAppend(value);
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
        stringValueAppender.validateAndAppend(value);
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

    public Condition greaterThanAny(SelectQueryBuilder subQuery) {
        query.append(GREATER_THAN);
        subQueryAppender.appendConditionWithSubQuery("ANY ", subQuery);
        return getCondition();
    }

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
        stringValueAppender.validateAndAppend(value);
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

    public Condition greaterThanOrEqualAny(SelectQueryBuilder subQuery) {
        query.append(GREATER_THAN_OR_EQUAL);
        subQueryAppender.appendConditionWithSubQuery("ANY ", subQuery);
        return getCondition();
    }

    public Condition greaterThanOrEqualAll(SelectQueryBuilder subQuery) {
        query.append(GREATER_THAN_OR_EQUAL);
        subQueryAppender.appendConditionWithSubQuery("ALL ", subQuery);
        return getCondition();
    }

    public Condition lesserThan(String value) {
        query.append(LESSER_THAN);
        stringValueAppender.validateAndAppend(value);
        return getCondition();
    }

    public Condition lesserThan(int value) {
        query.append(LESSER_THAN);
        query.append(value);
        return getCondition();
    }

    public Condition lesserThan(double value) {
        query.append(LESSER_THAN);
        query.append(value);
        return getCondition();
    }

    public Condition lesserThanAny(SelectQueryBuilder subQuery) {
        query.append(LESSER_THAN);
        subQueryAppender.appendConditionWithSubQuery("ANY ", subQuery);
        return getCondition();
    }

    public Condition lesserThanAll(SelectQueryBuilder subQuery) {
        query.append(LESSER_THAN);
        subQueryAppender.appendConditionWithSubQuery("ALL ", subQuery);
        return getCondition();
    }

    public Condition lesserThanOrEqual(String value) {
        query.append(LESSER_THAN_OR_EQUAL);
        stringValueAppender.validateAndAppend(value);
        return getCondition();
    }

    public Condition lesserThanOrEqual(int value) {
        query.append(LESSER_THAN_OR_EQUAL);
        query.append(value);
        return getCondition();
    }

    public Condition lesserThanOrEqual(double value) {
        query.append(LESSER_THAN_OR_EQUAL);
        query.append(value);
        return getCondition();
    }

    public Condition lesserThanOrEqualAny(SelectQueryBuilder subQuery) {
        query.append(LESSER_THAN_OR_EQUAL);
        subQueryAppender.appendConditionWithSubQuery("ANY ", subQuery);
        return getCondition();
    }

    public Condition lesserThanOrEqualAll(SelectQueryBuilder subQuery) {
        query.append(LESSER_THAN_OR_EQUAL);
        subQueryAppender.appendConditionWithSubQuery("ALL ", subQuery);
        return getCondition();
    }

    public Condition startsWith(String pattern) {
        stringValueAppender.validate(pattern);
        String startsWith = pattern.concat("%");
        query.append(" LIKE ");
        stringValueAppender.appendStringValue(startsWith);
        return getCondition();
    }

    public Condition endsWith(String pattern) {
        stringValueAppender.validate(pattern);
        String endsWith = "%".concat(pattern);
        query.append(" LIKE ");
        stringValueAppender.appendStringValue(endsWith);
        return getCondition();
    }

    public Condition contains(String pattern) {
        stringValueAppender.validate(pattern);
        String contains = "%".concat(pattern).concat("%");
        query.append(" LIKE ");
        stringValueAppender.appendStringValue(contains);
        return getCondition();
    }

    public Condition isBetween(String lower, String higher) {
        query.append(" BETWEEN ");
        stringValueAppender.validateAndAppend(lower);
        query.append(" AND ");
        stringValueAppender.validateAndAppend(higher);
        return getCondition();
    }

    public Condition isBetween(int lower, int higher) {
        query.append(" BETWEEN ");
        query.append(lower);
        query.append(" AND ");
        query.append(higher);
        return getCondition();
    }

    public Condition isBetween(double lower, double higher) {
        query.append(" BETWEEN ");
        query.append(lower);
        query.append(" AND ");
        query.append(higher);
        return getCondition();
    }

    public Condition isNull() {
        query.append(" IS NULL");
        return getCondition();
    }

    public Condition isNotNull() {
        query.append(" IS NOT NULL");
        return getCondition();
    }

    public Condition isIn(String ...listOfValues) {
        query.append(" IN ");
        stringValueAppender.validateAndAppendList(listOfValues);
        return getCondition();
    }

    public Condition isIn(int ...listOfValues) {
        query.append(" IN ");
        QueryAppender.appendListOfValues(query, listOfValues);
        return getCondition();
    }

    public Condition isIn(double ...listOfValues) {
        query.append(" IN ");
        QueryAppender.appendListOfValues(query, listOfValues);
        return getCondition();
    }

    public Condition isInSub(SelectQueryBuilder subQuery) {
        subQueryAppender.appendConditionWithSubQuery(" IN ", subQuery);
        return getCondition();
    }

    private Condition getCondition() {
        return new Condition(query);
    }
}
