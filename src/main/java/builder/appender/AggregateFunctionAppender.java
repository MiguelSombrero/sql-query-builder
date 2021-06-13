package builder.appender;

import clause.Clause;

public class AggregateFunctionAppender {

    /**
     * Validates user input and appends 'COUNT(column)' into
     * clause string 'SELECT aggregate function(s)'
     *
     * @param clause clause object where string is appended
     *
     * @param column Column name to be appended in the COUNT() clause'
     */
    public static void count(Clause clause, String column) {
        clause.append("COUNT");
        applyAggregate(clause, column);
    }

    /**
     * Appends 'COUNT(*)' into
     * clause string 'SELECT aggregate function(s)'
     *
     * @param clause clause object where string is appended
     */
    public static void countAll(Clause clause) {
        clause.append("COUNT(*)");
    }

    /**
     * Validates user input and appends 'MIN(column)' into
     * clause string 'SELECT aggregate function(s)'
     *
     * @param clause clause object where string is appended
     *
     * @param column Column name to be appended in the MIN() clause
     */
    public static void min(Clause clause, String column) {
        clause.append("MIN");
        applyAggregate(clause, column);
    }

    /**
     * Validates user input and appends 'MAX(column)' into
     * clause string 'SELECT aggregate function(s)'
     *
     * @param clause clause object where string is appended
     *
     * @param column Column name to be appended in the MAX() clause
     */
    public static void max(Clause clause, String column) {
        clause.append("MAX");
        applyAggregate(clause, column);
    }

    /**
     * Validates user input and appends 'AVG(column)' into
     * clause string 'SELECT aggregate function(s)'
     *
     * @param clause clause object where string is appended
     *
     * @param column Column name to be appended in the AVG() clause
     */
    public static void avg(Clause clause, String column) {
        clause.append("AVG");
        applyAggregate(clause, column);
    }

    /**
     * Validates user input and appends 'SUM(column)' into
     * clause string 'SELECT aggregate function(s)'
     *
     * @param clause clause object where string is appended
     *
     * @param column Column name to be appended in the SUM() clause
     */
    public static void sum(Clause clause, String column) {
        clause.append("SUM");
        applyAggregate(clause, column);
    }

    private static void applyAggregate(Clause clause, String toColumn) {
        clause.append("(");
        StringAppender.validateAndAppend(clause, toColumn);
        clause.append(")");
    }

}
