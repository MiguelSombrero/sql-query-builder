package builder.clause;

import query.Clause;

public class Condition extends TerminalClauseOperation {

    public Condition(Clause clause) {
        super(clause);
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
    public Condition and(Condition condition) {
        clause.append(" AND ");
        clause.mergeClause(condition.build());
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
    public Condition or(Condition condition) {
        clause.append(" OR ");
        clause.mergeClause(condition.build());
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
    public Condition orSub(Condition condition) {
        clause.append(" OR (");
        clause.mergeClause(condition.build());
        clause.append(")");
        return this;
    }
}
