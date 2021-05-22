package builder.statement.select.table;

import builder.query.Query;
import builder.condition.Condition;
import builder.statement.select.order.Grouper;
import factory.ValidatorFactory;
import validation.Validator;

public class JoinTable extends Grouper {
    protected static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    public JoinTable(Query query) {
        super(query);
    }

    /**
     * Appends 'WHERE' to 'SELECT ... FROM table WHERE condition' statement.
     *
     * @param clause Where clause to be appended in the query. Where clause
     * is build with WhereClauseFactory factory class.
     *
     * @return Grouper class which can be used to create
     * GROUP BY statements or proceed further in SQL
     */
    public Grouper where(Condition clause) {
        query.append(" WHERE ");
        query.append(clause.build());
        return new Grouper(query);
    }

    /**
     * Appends 'INNER JOIN table' into join statement.
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
     * Appends 'LEFT JOIN table' into join statement.
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
     * Appends 'RIGHT JOIN table' into join statement.
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
        validator.validate(table);
        query.append(table);
        return new On(query);
    }
}
