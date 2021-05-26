package builder.condition;

import builder.QueryBuilder;
import builder.Query;
import builder.TerminalOperation;

public class Condition extends TerminalOperation {

    public Condition(Query query) {
        super(query);
    }

    /**
     * Appends 'AND condition' into 'WHERE ... AND condition' clause.
     *
     * @param condition Condition created by WhereClauseFactory or
     * HavingClauseFactory, depending whether it is WHERE or
     * HAVING clause condition is appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition and(QueryBuilder condition) {
        query.append(" AND ");
        query.append(condition.build());
        return this;
    }

    /**
     * Appends 'OR condition' into 'WHERE ... OR condition' clause.
     *
     * @param condition Condition created by WhereClauseFactory or
     * HavingClauseFactory, depending whether it is WHERE or
     * HAVING clause condition is appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition or(QueryBuilder condition) {
        query.append(" OR ");
        query.append(condition.build());
        return this;
    }

    /**
     * Appends 'OR (condition)' into 'WHERE ... OR (condition)' clause.
     * Method can be used for appending embedded conditions like
     * OR (condition1 AND condition2).
     *
     * @param condition Condition created by WhereClauseFactory or
     * HavingClauseFactory, depending whether it is WHERE or
     * HAVING clause condition is appended
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Condition orSub(QueryBuilder condition) {
        query.append(" OR (");
        query.append(condition.build());
        query.append(")");
        return this;
    }
}
