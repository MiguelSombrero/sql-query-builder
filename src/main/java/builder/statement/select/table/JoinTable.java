package builder.statement.select.table;

import builder.clause.Condition;
import builder.statement.select.order.Grouper;
import builder.appender.StringAppender;
import query.dql.SelectQuery;

public class JoinTable extends Grouper {
    protected StringAppender stringAppender;

    public JoinTable(SelectQuery query) {
        super(query);
        this.stringAppender = new StringAppender(query);
    }

    /**
     * Appends 'WHERE' to 'SELECT ... FROM table WHERE condition' statement.
     *
     * @param condition Where clause to be appended in the query. Where clause
     * is build with WhereClauseFactory factory class.
     *
     * @return Grouper class which can be used to create
     * GROUP BY statements or proceed further in SQL
     */
    public Grouper where(Condition condition) {
        query.append(" WHERE ");
        query.mergeSubQuery(condition.build());
        return new Grouper(query);
    }

    /**
     * Validates user input and appends
     * 'INNER JOIN table' into join statement.
     *
     * @param table Table name to be joined
     *
     * @return On class that can be used to
     * append 'ON column = joinColumn' into
     * join statement and alias join table
     */
    public On innerJoin(String table) {
        query.append(" INNER JOIN ");
        return join(table);
    }

    /**
     * Validates user input and appends
     * 'LEFT JOIN table' into join statement.
     *
     * @param table Table name to be joined
     *
     * @return On class that can be used to
     * append 'ON column = joinColumn' into
     * join statement and alias join table
     */
    public On leftJoin(String table) {
        query.append(" LEFT JOIN ");
        return join(table);
    }

    /**
     * Validates user input and appends
     * 'RIGHT JOIN table' into join statement.
     *
     * @param table Table name to be joined
     *
     * @return On class that can be used to
     * append 'ON column = joinColumn' into
     * join statement and alias join table
     */
    public On rightJoin(String table) {
        query.append(" RIGHT JOIN ");
        return join(table);
    }

    private On join(String table) {
        stringAppender.validateAndAppend(table);
        return new On(query);
    }
}
