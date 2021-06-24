package builder.query.select.table;

import builder.appender.StringAppender;
import query.Clause;

public class AliasedOn {

    protected Clause clause;

    public AliasedOn(Clause clause) {
        this.clause = clause;
    }

    /**
     * Validates user input and appends 'ON column = joinColumn' into
     * query string 'SELECT ... JOIN table ON column = joinColumn'.
     *
     * @param column First column name used for join
     *
     * @param joinColumn Second column name user for join
     *
     * @return JoinTable class which can be used to create
     * more joins or proceed to WHERE, GROUP BY, etc. clauses
     */
    public JoinTable on(String column, String joinColumn) {
        clause.append(" ON ");
        StringAppender.validateAndAppend(clause, column);
        clause.append(" = ");
        StringAppender.validateAndAppend(clause, joinColumn);
        return new JoinTable(clause);
    }
}
