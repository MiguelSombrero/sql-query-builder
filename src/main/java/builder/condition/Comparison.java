package builder.condition;

import builder.query.Query;
import builder.statement.select.SelectQueryBuilder;

public class Comparison {

    protected Query query;

    private StringComparator stringComparator;
    private IntegerComparator integerComparator;
    private DoubleComparator doubleComparator;
    private SubQueryComparator subQueryComparator;

    public Comparison(Query query) {
        this.query = query;
        this.stringComparator = new StringComparator(query);
        this.integerComparator = new IntegerComparator(query);
        this.doubleComparator = new DoubleComparator(query);
        this.subQueryComparator = new SubQueryComparator(query);
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
        stringComparator.equals(value);
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
        integerComparator.equals(value);
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
        doubleComparator.equals(value);
        return getCondition();
    }

    /**
     * Appends '= ANY (select sub-query)' into
     * 'WHERE operand = ANY (select sub-query)' clause.
     *
     * @param query SELECT sub-query to be appended. Sub-query
     * can be created with factory class QueryFactory
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition equalsAny(SelectQueryBuilder query) {
        subQueryComparator.equalsAny(query);
        return getCondition();
    }

    /**
     * Appends '= ALL (select sub-query)' into
     * 'WHERE operand = ALL (select sub-query)' clause.
     *
     * @param query SELECT sub-query to be appended. Sub-query
     * can be created with factory class QueryFactory
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition equalsAll(SelectQueryBuilder query) {
        subQueryComparator.equalsAll(query);
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
        stringComparator.greaterThan(value);
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
        integerComparator.greaterThan(value);
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
        doubleComparator.greaterThan(value);
        return getCondition();
    }

    public Condition greaterThanAny(SelectQueryBuilder query) {
        subQueryComparator.greaterThanAny(query);
        return getCondition();
    }

    public Condition greaterThanAll(SelectQueryBuilder query) {
        subQueryComparator.greaterThanAll(query);
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
        stringComparator.greaterThanOrEqual(value);
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
        integerComparator.greaterThanOrEqual(value);
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
        doubleComparator.greaterThanOrEqual(value);
        return getCondition();
    }

    public Condition greaterThanOrEqualAny(SelectQueryBuilder query) {
        subQueryComparator.greaterThanOrEqualAny(query);
        return getCondition();
    }

    public Condition greaterThanOrEqualAll(SelectQueryBuilder query) {
        subQueryComparator.greaterThanOrEqualAll(query);
        return getCondition();
    }

    public Condition lesserThan(String value) {
        stringComparator.lesserThan(value);
        return getCondition();
    }

    public Condition lesserThan(int value) {
        integerComparator.lesserThan(value);
        return getCondition();
    }

    public Condition lesserThan(double value) {
        doubleComparator.lesserThan(value);
        return getCondition();
    }

    public Condition lesserThanAny(SelectQueryBuilder query) {
        subQueryComparator.lesserThanAny(query);
        return getCondition();
    }

    public Condition lesserThanAll(SelectQueryBuilder query) {
        subQueryComparator.lesserThanAll(query);
        return getCondition();
    }

    public Condition lesserThanOrEqual(String value) {
        stringComparator.lesserThanOrEqual(value);
        return getCondition();
    }

    public Condition lesserThanOrEqual(int value) {
        integerComparator.lesserThanOrEqual(value);
        return getCondition();
    }

    public Condition lesserThanOrEqual(double value) {
        doubleComparator.lesserThanOrEqual(value);
        return getCondition();
    }

    public Condition lesserThanOrEqualAny(SelectQueryBuilder query) {
        subQueryComparator.lesserThanOrEqualAny(query);
        return getCondition();
    }

    public Condition lesserThanOrEqualAll(SelectQueryBuilder query) {
        subQueryComparator.lesserThanOrEqualAll(query);
        return getCondition();
    }

    public Condition startsWith(String pattern) {
        stringComparator.startsWith(pattern);
        return getCondition();
    }

    public Condition endsWith(String pattern) {
        stringComparator.endsWith(pattern);
        return getCondition();
    }

    public Condition contains(String pattern) {
        stringComparator.contains(pattern);
        return getCondition();
    }

    public Condition isInSub(SelectQueryBuilder query) {
        subQueryComparator.isInSub(query);
        return getCondition();
    }

    public Condition isBetween(String lower, String higher) {
        stringComparator.isBetween(lower, higher);
        return getCondition();
    }

    public Condition isBetween(int lower, int higher) {
        integerComparator.isBetween(lower, higher);
        return getCondition();
    }

    public Condition isBetween(double lower, double higher) {
        doubleComparator.isBetween(lower, higher);
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
        stringComparator.isIn(listOfValues);
        return getCondition();
    }

    public Condition isIn(int ...listOfValues) {
        integerComparator.isIn(listOfValues);
        return getCondition();
    }

    public Condition isIn(double ...listOfValues) {
        doubleComparator.isIn(listOfValues);
        return getCondition();
    }

    private Condition getCondition() {
        return new Condition(query);
    }
}
