package builder.appender;

import query.Statement;

public class AggregateFunctionAppender {

    /**
     * Validates user input and appends 'COUNT(column)' into
     * clause string 'SELECT aggregate function(s)'
     *
     * @param statement clause object where string is appended
     *
     * @param column Column name to be appended in the COUNT() clause'
     */
    public static void count(Statement statement, String column) {
        statement.append("COUNT");
        applyAggregate(statement, column);
    }

    /**
     * Appends 'COUNT(*)' into
     * clause string 'SELECT aggregate function(s)'
     *
     * @param statement clause object where string is appended
     */
    public static void countAll(Statement statement) {
        statement.append("COUNT(*)");
    }

    /**
     * Validates user input and appends 'MIN(column)' into
     * clause string 'SELECT aggregate function(s)'
     *
     * @param statement clause object where string is appended
     *
     * @param column Column name to be appended in the MIN() clause
     */
    public static void min(Statement statement, String column) {
        statement.append("MIN");
        applyAggregate(statement, column);
    }

    /**
     * Validates user input and appends 'MAX(column)' into
     * clause string 'SELECT aggregate function(s)'
     *
     * @param statement clause object where string is appended
     *
     * @param column Column name to be appended in the MAX() clause
     */
    public static void max(Statement statement, String column) {
        statement.append("MAX");
        applyAggregate(statement, column);
    }

    /**
     * Validates user input and appends 'AVG(column)' into
     * clause string 'SELECT aggregate function(s)'
     *
     * @param statement clause object where string is appended
     *
     * @param column Column name to be appended in the AVG() clause
     */
    public static void avg(Statement statement, String column) {
        statement.append("AVG");
        applyAggregate(statement, column);
    }

    /**
     * Validates user input and appends 'SUM(column)' into
     * clause string 'SELECT aggregate function(s)'
     *
     * @param statement clause object where string is appended
     *
     * @param column Column name to be appended in the SUM() clause
     */
    public static void sum(Statement statement, String column) {
        statement.append("SUM");
        applyAggregate(statement, column);
    }

    private static void applyAggregate(Statement statement, String toColumn) {
        statement.append("(");
        StringAppender.validateAndAppend(statement, toColumn);
        statement.append(")");
    }

}
